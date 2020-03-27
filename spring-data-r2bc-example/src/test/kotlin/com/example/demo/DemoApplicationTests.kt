package com.example.demo

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.function.BodyInserters
import reactor.test.StepVerifier
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class DemoApplicationTests(
    @LocalServerPort val port: Int,
    @Autowired val repository: PersonRepository
) {
    private lateinit var client: WebTestClient

    @BeforeEach
    fun setUp() {
        client = WebTestClient.bindToServer()
            .baseUrl("http://localhost:$port")
            .build()

        repository.deleteAll().block()
    }

    @Test
    fun `test save person data`() {
        val person = generateRandomPerson()
        client.post()
            .uri("/persons")
            .body(BodyInserters.fromValue(person))
            .exchange()
            .expectStatus().isCreated

        repository.findAll()
            .filter { it.firstname == person.firstname }
            .count()
            .`as` { StepVerifier.create(it) }
            .expectNext(1)
            .verifyComplete()
    }

    @Test
    fun `test findAll person data`() {
        savePerson()
        savePerson()
        savePerson()

        client.get()
            .uri("/persons")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").value(`is`(3))
    }

    @Test
    fun `test findById`() {
        val savedPerson = savePerson()

        client.get()
            .uri("/persons/${savedPerson.id}")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Person>().isEqualTo(savedPerson)
    }

    @Test
    fun `test findById if entity not exist`() {
        client.get()
            .uri("/persons/${Random.nextLong()}")
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `test update`() {
        val savePerson = savePerson()
        val person = generateRandomPerson()
        val id = savePerson.id!!

        client.put()
            .uri("/persons/$id")
            .bodyValue(person)
            .exchange()
            .expectStatus().isOk

        val expected = person.copy(id = id)
        repository.findById(id)
            .`as` { StepVerifier.create(it) }
            .expectNext(expected)
            .verifyComplete()
    }

    @Test
    fun `test update if entity not exist`() {
        val person = generateRandomPerson()
        client.put()
            .uri("/persons/${Random.nextLong()}")
            .bodyValue(person)
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `test delete`() {
        val person = savePerson()
        val id = person.id!!
        client.delete()
            .uri("/persons/$id")
            .exchange()
            .expectStatus().isOk

        repository.existsById(id)
            .`as` { StepVerifier.create(it) }
            .expectNext(false)
            .verifyComplete()
    }

    @Test
    fun `test delete if entity not exist`() {
        client.delete()
            .uri("/persons/${Random.nextLong()}")
            .exchange()
            .expectStatus().isBadRequest
    }

    private fun savePerson(): Person {
        val person = generateRandomPerson()
        return repository.save(person).block()!!
    }

    private fun generateRandomPerson(id: Long? = null) = Person(
        id = id,
        firstname = UUID.randomUUID().toString(),
        lastname = UUID.randomUUID().toString()
    )
}

