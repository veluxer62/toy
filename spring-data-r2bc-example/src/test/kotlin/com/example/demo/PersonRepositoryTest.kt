package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [R2bcConfiguration::class])
internal class PersonRepositoryTest(
    @Autowired private val personRepository: PersonRepository,
    @Autowired private val databaseClient: DatabaseClient
) {

    @BeforeEach
    fun beforeEach() {
        databaseClient
            .execute("delete from person")
            .fetch()
            .rowsUpdated()
            .block()
    }

    @Test
    fun `test bean loaded`() {
        assertThat(personRepository).isNotNull
        assertThat(databaseClient).isNotNull
    }

    @Test
    fun `test save`() {
        val person = generatePerson()
        personRepository.save(person)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(1)
            .verifyComplete()

        val selectOne = databaseClient
            .execute(
                """
                    select count(*) from person 
                    where firstname='${person.firstname}' and lastname='${person.lastname}'
                """.trimIndent()
            )
            .asType<Long>()
            .fetch()
            .one()

        StepVerifier
            .create(selectOne)
            .expectNext(1)
            .verifyComplete()
    }

    @Test
    fun `test saveAll`() {
        val persons = listOf(
            generatePerson(),
            generatePerson(),
            generatePerson()
        )
        personRepository.saveAll(persons)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(3)
            .verifyComplete()

        val findPersons = databaseClient
            .execute("select * from person")
            .asType<Person>()
            .fetch()
            .all()

        StepVerifier
            .create(findPersons)
            .expectNextCount(3)
            .verifyComplete()
    }

    @Test
    fun `test update`() {
        val givenPerson = insertPerson(Random.nextLong())

        val person = givenPerson.copy(
            firstname = UUID.randomUUID().toString(),
            lastname = UUID.randomUUID().toString()
        )
        personRepository.save(person)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(1)
            .verifyComplete()

        val expected = databaseClient
            .execute("select * from person where id = ${person.id}")
            .asType<Person>()
            .fetch()
            .one()

        StepVerifier
            .create(expected)
            .expectNext(person)
            .verifyComplete()
    }

    @Test
    fun `test updateAll`() {
        val givenPerson1 = insertPerson(Random.nextLong())
        val givenPerson2 = insertPerson(Random.nextLong())

        val persons = listOf(
            givenPerson1.copy(
                firstname = UUID.randomUUID().toString(),
                lastname = UUID.randomUUID().toString()
            ),
            givenPerson2.copy(
                firstname = UUID.randomUUID().toString(),
                lastname = UUID.randomUUID().toString()
            )
        )

        personRepository.saveAll(persons)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(2)
            .verifyComplete()

        val expected = databaseClient
            .execute("select * from person where id in (${givenPerson1.id}, ${givenPerson2.id})")
            .asType<Person>()
            .fetch()
            .all()

        StepVerifier
            .create(expected)
            .expectNextCount(2)
            .verifyComplete()
    }

    @Test
    fun `test findAll`() {
        insertPerson()
        insertPerson()
        insertPerson()

        personRepository.findAll()
            .`as` { StepVerifier.create(it) }
            .expectNextCount(3)
            .verifyComplete()
    }

    @Test
    fun `test count`() {
        insertPerson()
        insertPerson()
        insertPerson()

        personRepository.count()
            .`as` { StepVerifier.create(it) }
            .expectNext(3)
            .verifyComplete()
    }

    @Test
    fun `test findById`() {
        val id = Random.nextLong()
        val givenPerson = insertPerson(id)

        personRepository.findById(id)
            .`as` { StepVerifier.create(it) }
            .expectNext(givenPerson)
            .verifyComplete()

        personRepository.findById(Random.nextLong())
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun `test existById`() {
        val id = Random.nextLong()
        insertPerson(id)
        personRepository.existsById(id)
            .`as` { StepVerifier.create(it) }
            .expectNext(true)
            .verifyComplete()

        personRepository.existsById(Random.nextLong())
            .`as` { StepVerifier.create(it) }
            .expectNext(false)
            .verifyComplete()
    }

    @Test
    fun `test deleteAll`() {
        insertPerson()
        insertPerson()
        insertPerson()

        personRepository.deleteAll()
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()

        val expected = databaseClient
            .execute("select count(*) from person")
            .asType<Long>()
            .fetch()
            .all()

        StepVerifier
            .create(expected)
            .expectNext(0)
            .verifyComplete()
    }

    @Test
    fun `test deleteById`() {
        val id = Random.nextLong()
        insertPerson(id)

        personRepository.deleteById(id)
            .`as` { StepVerifier.create(it) }
            .expectNextCount(0)
            .verifyComplete()

        val expected = databaseClient
            .execute("select count(*) from person where id = $id")
            .asType<Long>()
            .fetch()
            .all()

        StepVerifier
            .create(expected)
            .expectNext(0)
            .verifyComplete()
    }
    
    @Test
    fun `test custom method findAllByFirstname`() {
        insertPerson()
        insertPerson()
        val person = insertPerson()

        personRepository.findAllByFirstname(firstname = person.firstname)
            .`as` { StepVerifier.create(it) }
            .expectNextMatches {
                it.firstname == person.firstname
            }
            .verifyComplete()
    }

    private fun insertPerson(id: Long? = null): Person {
        val givenPerson = generatePerson(id)
        databaseClient.insert().into(Person::class.java)
            .using(givenPerson)
            .fetch()
            .rowsUpdated()
            .block()
        return givenPerson
    }

    private fun generatePerson(id: Long? = null): Person {
        return Person(
            id = id,
            firstname = UUID.randomUUID().toString(),
            lastname = UUID.randomUUID().toString()
        )
    }

}