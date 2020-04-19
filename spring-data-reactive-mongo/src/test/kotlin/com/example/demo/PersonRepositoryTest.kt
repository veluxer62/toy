package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@DataMongoTest
class PersonRepositoryTest {

    @Autowired
    private lateinit var template: ReactiveMongoTemplate

    @Autowired
    private lateinit var repository: PersonRepository

    @BeforeEach
    fun beforeEach() {
        template.dropCollection(Person::class.java).subscribe()
    }

    @Test
    fun `save will create data correctly`() {
        // Arrange
        val name = UUID.randomUUID().toString()
        val age = Random.nextInt()
        val document = Person(
            id = null,
            name = name,
            age = age
        )

        // Act
        repository.save(document)
            .`as` { StepVerifier.create(it) }
            .assertNext {
                assertThat(it.id).isNotNull()
                assertThat(it.name).isEqualTo(name)
                assertThat(it.age).isEqualTo(age)
            }
            .verifyComplete()
    }

    @Test
    fun `save will update data correctly`() {
        // Arrange
        val savedPerson = generateDocument()

        val name = UUID.randomUUID().toString()
        val age = Random.nextInt()

        val id = savedPerson.id.orEmpty()
        val updateDocument = Person(
            id = id,
            name = name,
            age = age
        )

        // Act & Assert
        repository.save(updateDocument)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(1)
            .verifyComplete()

        template.findById(id, Person::class.java)
            .`as` { StepVerifier.create(it) }
            .assertNext {
                assertThat(it.id).isEqualTo(id)
                assertThat(it.name).isEqualTo(name)
                assertThat(it.age).isEqualTo(age)
            }
            .verifyComplete()
    }

    @Test
    fun `findAll will return flow of document correctly`() {
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
    fun `findById will return document correctly`() {
        // Arrange
        val savedData = generateDocument()

        // Act & Assert
        repository.findById(savedData.id.orEmpty())
            .`as` { StepVerifier.create(it) }
            .expectNext(savedData)
            .verifyComplete()
    }

    @Test
    fun `count will return count of document correctly`() {
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
    fun `deleteById will delete document correctly`() {
        // Arrange
        val document = generateDocument()

        // Act
        repository.deleteById(document.id.orEmpty())
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()

        // Assert
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