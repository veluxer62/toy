package com.example.demo

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.net.URI
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@WebFluxTest(Router::class, PersonHandler::class)
class PersonRouterTest(
    @Autowired private val sut: WebTestClient
) {
    @MockBean
    private lateinit var repository: PersonRepository

    @Test
    fun `test save`() {
        val name = UUID.randomUUID().toString()
        val age = Random.nextInt()
        val body = """
            {
                "name": "$name",
                "age": $age
            }
        """.trimIndent()
        val person = Person(null, name, age)

        Mockito.`when`(repository.save(person))
            .thenReturn(person.toMono())

        sut.post()
            .uri("/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .exchange()
            .expectStatus().is2xxSuccessful

        Mockito.verify(repository)
            .save(person)
    }

    @Test
    fun `test update`() {
        val id = UUID.randomUUID().toString()
        val name = UUID.randomUUID().toString()
        val age = Random.nextInt()
        val body = """
            {
                "name": "$name",
                "age": $age
            }
        """.trimIndent()
        val person = Person(id, name, age)

        Mockito.`when`(repository.existsById(id))
            .thenReturn(true.toMono())
        Mockito.`when`(repository.save(person))
            .thenReturn(person.toMono())

        sut.put()
            .uri("/persons/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .exchange()
            .expectStatus().is2xxSuccessful

        Mockito.verify(repository)
            .save(person)
    }

    @Test
    fun `test update if data not exist`() {
        val id = UUID.randomUUID().toString()
        val name = UUID.randomUUID().toString()
        val age = Random.nextInt()
        val body = """
            {
                "name": "$name",
                "age": $age
            }
        """.trimIndent()
        val person = Person(id, name, age)

        Mockito.`when`(repository.existsById(id))
            .thenReturn(false.toMono())
        Mockito.`when`(repository.save(person))
            .thenReturn(person.toMono())

        sut.put()
            .uri("/persons/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .exchange()
            .expectStatus().isBadRequest

        Mockito.verify(repository, never())
            .save(person)
    }

    @Test
    fun `test findAll`() {
        Mockito.`when`(repository.findAll())
            .thenReturn(
                listOf(
                    generatePerson(),
                    generatePerson(),
                    generatePerson()
                ).toFlux()
            )

        sut.get()
            .uri("/persons")
            .exchange()
            .expectStatus().isOk
            .expectBody<List<Person>>()
            .consumeWith {
                assertThat(it.responseBody).isNotNull
                assertThat(it.responseBody?.size).isEqualTo(3)
            }
    }

    @Test
    fun `test findById`() {
        val id = UUID.randomUUID().toString()
        val person = Person(
            id = id,
            name = UUID.randomUUID().toString(),
            age = Random.nextInt()
        )

        Mockito.`when`(repository.findById(id))
            .thenReturn(person.toMono())

        sut.get()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isOk
            .expectBody<Person>()
            .consumeWith {
                assertThat(it.responseBody).isNotNull()
                assertThat(it.responseBody).isEqualTo(person)
            }
    }

    @Test
    fun `test findById data not exist`() {
        val id = UUID.randomUUID().toString()

        Mockito.`when`(repository.findById(id))
            .thenReturn(Mono.empty())

        sut.get()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `test delete`() {
        val id = UUID.randomUUID().toString()

        Mockito.`when`(repository.existsById(id))
            .thenReturn(true.toMono())
        Mockito.`when`(repository.deleteById(id))
            .thenReturn(Mono.empty())

        sut.delete()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isOk

        Mockito.verify(repository)
            .deleteById(id)
    }

    @Test
    fun `test delete if data not exist`() {
        val id = UUID.randomUUID().toString()

        Mockito.`when`(repository.existsById(id))
            .thenReturn(false.toMono())

        sut.delete()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isBadRequest

        Mockito.verify(repository, never())
            .deleteById(id)
    }

    private fun generatePerson(id: String? = UUID.randomUUID().toString()) =
        Person(
            id = id,
            name = UUID.randomUUID().toString(),
            age = Random.nextInt()
        )

}