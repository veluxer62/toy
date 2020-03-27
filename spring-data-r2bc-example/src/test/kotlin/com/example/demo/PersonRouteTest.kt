package com.example.demo

import org.assertj.core.api.Assertions
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [Routes::class, PersonHandler::class, PersonRepository::class])
@WebFluxTest
internal class PersonRouteTest {
    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var personRepository: PersonRepository

    @Test
    fun `test bean loaded`() {
        Assertions.assertThat(client).isNotNull
        Assertions.assertThat(personRepository).isNotNull
    }

    @Test
    fun `test get all persons`() {
        Mockito.`when`(personRepository.findAll())
            .thenReturn(
                listOf(
                    generateRandomPerson(),
                    generateRandomPerson(),
                    generateRandomPerson()
                ).toFlux()
            )

        client.get()
            .uri("/persons")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").value(Matchers.`is`(3))
    }

    @Test
    fun `test get one person`() {
        val id = Random.nextLong()
        val person = generateRandomPerson(id)
        Mockito.`when`(personRepository.findById(id))
            .thenReturn(person.toMono())

        client.get()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Person>().isEqualTo(person)
    }

    @Test
    fun `test get one person if it is not exists`() {
        val id = Random.nextLong()
        Mockito.`when`(personRepository.findById(id))
            .thenReturn(Mono.empty())

        client.get()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `test post person`() {
        val person = generateRandomPerson()

        Mockito.`when`(personRepository.save(person))
            .thenReturn(person.toMono())

        client.post()
            .uri("/persons")
            .body(BodyInserters.fromValue(person))
            .exchange()
            .expectStatus().isCreated

        Mockito.verify(personRepository).save(person)
    }

    @Test
    fun `test put person`() {
        val id = Random.nextLong()
        val person = generateRandomPerson(id)

        Mockito.`when`(personRepository.existsById(id))
            .thenReturn(true.toMono())
        Mockito.`when`(personRepository.save(person))
            .thenReturn(person.toMono())

        client.put()
            .uri("/persons/$id")
            .bodyValue(person)
            .exchange()
            .expectStatus().isOk

        Mockito.verify(personRepository).save(person)
    }

    @Test
    fun `test put person if it is not exist`() {
        val id = Random.nextLong()
        val person = generateRandomPerson(id)

        Mockito.`when`(personRepository.existsById(id))
            .thenReturn(false.toMono())

        client.put()
            .uri("/persons/$id")
            .bodyValue(person)
            .exchange()
            .expectStatus().isBadRequest

        Mockito.verify(personRepository, never()).save(person)
    }

    @Test
    fun `test delete person`() {
        val id = Random.nextLong()

        Mockito.`when`(personRepository.existsById(id))
            .thenReturn(true.toMono())
        Mockito.`when`(personRepository.deleteById(id))
            .thenReturn(Mono.empty())

        client.delete()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isOk

        Mockito.verify(personRepository).deleteById(id)
    }

    @Test
    fun `test delete person if it is not exist`() {
        val id = Random.nextLong()
        Mockito.`when`(personRepository.existsById(id))
            .thenReturn(false.toMono())

        client.delete()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isBadRequest

        Mockito.verify(personRepository, never()).deleteById(id)
    }

    private fun generateRandomPerson(id: Long? = null) = Person(
        id = id,
        firstname = UUID.randomUUID().toString(),
        lastname = UUID.randomUUID().toString()
    )
}