package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import reactor.test.StepVerifier
import java.util.*
import kotlin.random.Random

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests(
    @Autowired
    private val repository: PersonRepository,
    @Autowired
    private val template: ReactiveMongoTemplate
) {

    @BeforeEach
    fun beforeEach() {
        template.dropCollection(Person::class.java).subscribe()
    }

    @Test
    fun `test save`() {
        val document = Person(
            id = null,
            name = UUID.randomUUID().toString(),
            age = Random.nextInt()
        )

        repository.save(document)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun `test update`() {
        // Arrange
        val savedDocument = generateDocument()
        val document = Person(
            id = savedDocument.id,
            name = UUID.randomUUID().toString(),
            age = Random.nextInt()
        )

        // Act & Assert
        repository.save(document)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun `test findAll`() {
        // Arrange
        generateDocument()
        generateDocument()
        generateDocument()

        // Act & Assert
        repository.findAll()
            .`as` { StepVerifier.create(it) }
            .expectNextCount(3)
            .verifyComplete()
    }

    @Test
    fun `test findById`() {
        // Arrange
        val document = generateDocument()

        // Act & Assert
        repository.findById(document.id.orEmpty())
            .`as` { StepVerifier.create(it) }
            .assertNext {
                assertThat(it).isEqualTo(document)
            }
            .verifyComplete()
    }

    @Test
    fun `test count`() {
        // Arrange
        generateDocument()
        generateDocument()
        generateDocument()

        // Act & Assert
        repository.count()
            .`as` { StepVerifier.create(it) }
            .expectNext(3)
            .verifyComplete()
    }

    @Test
    fun `test deleteById`() {
        // Arrange
        val document = generateDocument()

        // Act & Assert
        repository.deleteById(document.id.orEmpty())
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()

        template.findById(document.id.orEmpty(), Person::class.java)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()
    }


    private fun generateDocument(): Person {
        return template.insert(
            Person(
                null,
                UUID.randomUUID().toString(),
                Random.nextInt()
            )
        ).block()!!
    }

}
