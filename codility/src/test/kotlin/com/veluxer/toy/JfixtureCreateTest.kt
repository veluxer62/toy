package com.veluxer.toy

import com.flextrade.jfixture.JFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JfixtureCreateTest {
    lateinit var sut: JFixture

    @BeforeEach
    fun init() {
        sut = JFixture()
    }

    @Test
    fun `create should generate data`() {
        val actual = sut.create(Foo::class.java)

        assertThat(actual.mutableField).isNotEmpty()
        assertThat(actual.immutableField).isEqualTo(0)
    }
    
    @Test
    fun `create should generate different data`() {
        val first = sut.create(Bar::class.java)
        val second = sut.create(Bar::class.java)

        assertThat(first.mutableField).isNotEqualTo(second.mutableField)
        assertThat(first.immutableField).isNotEqualTo(second.immutableField)
    }

    @Test
    fun `create should generate data which have many kinds of types`() {
        val actual = sut.create(Baz::class.java)

        assertThat(actual.stringField).isNotEmpty()
        assertThat(actual.classField.immutableField).isNotEmpty()
        assertThat(actual.mapField).isNotEmpty
        assertThat(actual.listField).isNotEmpty
    }

    @Test
    fun `create should except if given interface`() {
        assertThrows<UnsupportedOperationException> {
            sut.create(Qux::class.java)
        }
    }

    @Test
    fun `create should generate data if given class implement interface`() {
        val actual = sut.create(QuxImpl::class.java)

        assertThat(actual.field).isNotEmpty()
        assertThat(actual.action()).isNotEmpty()
    }

    class Foo {
        var mutableField = ""
        val immutableField = 0
    }

    data class Bar(
            var mutableField: Long,
            val immutableField: String
    )

    data class Baz(
            val stringField: String,
            val classField: Bar,
            val objectField: Any,
            val mapField: Map<String, String>,
            val listField: List<String>,
            val enumField: BazEnum
    )

    enum class BazEnum{
        A,
        B,
        C,
        D
    }

    interface Qux {
        fun action(): String
    }

    class QuxImpl : Qux {
        lateinit var field: String
        override fun action(): String {
            return field
        }
    }
}