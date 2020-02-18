package com.kh.jfixture

import com.appmattus.kotlinfixture.kotlinFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest(showSql = true)
internal class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    private val fixture = kotlinFixture()

    @Test
    fun `test`() {
        // Arrange
        val user = fixture<User>()

        // Act
        userRepository.saveAndFlush(user.copy(id = 1))

        // Assert
        val result = testEntityManager.find(User::class.java, 1L)
        Assertions.assertEquals(user.name, result.name)
        Assertions.assertEquals(user.age, result.age)
        Assertions.assertEquals(user.gender, result.gender)
    }
}