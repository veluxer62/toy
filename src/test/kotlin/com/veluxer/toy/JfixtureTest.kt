package com.veluxer.toy

import com.flextrade.jfixture.JFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JfixtureTest {

    @Test
    fun `create should generate data`() {
        val sut = JFixture()

        val actual = sut.create(Foo::class.java)

        assertThat(actual.mutableField).isNotEmpty()
        assertThat(actual.immutableField).isEqualTo(0)
    }
    
    @Test
    fun `create should generate different data`() {
        val sut = JFixture()

        val actual = sut.create(Bar::class.java)
        val repeat = sut.create(Bar::class.java)

        assertThat(actual.mutableField).isNotEqualTo(repeat.mutableField)
        assertThat(actual.immutableField).isNotEqualTo(repeat.immutableField)
    }

    class Foo {
        var mutableField = ""
        val immutableField = 0
    }

    data class Bar(
            var mutableField: Long,
            val immutableField: String
    )
}