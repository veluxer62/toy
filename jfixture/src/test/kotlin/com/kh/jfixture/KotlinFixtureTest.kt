package com.kh.jfixture

import com.appmattus.kotlinfixture.kotlinFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import java.time.LocalDateTime
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

class KotlinFixtureTest {

    val fixture = kotlinFixture()

    @Test
    fun `test class doesn't have LocalDateTime`() {
        val result = fixture<SampleClass>()
        println(result.id)
        println(result.name)
        assertThat(result.id::class).isEqualTo(Long::class)
        assertThat(result.name::class).isEqualTo(String::class)
    }

    @Test
    fun `test class havs LocalDateTime`() {
        val result = fixture<SampleClass2>()
        println(result.id)
        println(result.name)
        println(result.time)
        assertThat(result.id::class).isEqualTo(Long::class)
        assertThat(result.name::class).isEqualTo(String::class)
        assertThat(result.time::class).isEqualTo(LocalDateTime::class)
    }

    @Test
    fun `test 3`() {
        val result = fixture<SampleClass3>()
        println(result.id)
        println(result.name)
        assertThat(result.id::class).isEqualTo(Long::class)
        assertThat(result.name::class).isEqualTo(String::class)
    }

    @Test
    fun `test 4`() {
        val result = fixture<SampleClass4>()
        println(result.id)
        println(result.name)
        println(result.time)
        assertThat(result.id::class).isEqualTo(Long::class)
        assertThat(result.name::class).isEqualTo(String::class)
        assertThat(result.time::class).isEqualTo(LocalDateTime::class)
    }

    @Test
    fun `test 5`() {
        val result = fixture<SampleClass5>()
        println(result.id)
        println(result.name)
        println(result.time)
        assertThat(result.id::class).isEqualTo(Long::class)
        assertThat(result.name::class).isEqualTo(String::class)
        assertThat(result.time::class).isEqualTo(Timestamp::class)
    }

    @Test
    fun `test 6`() {
        val result = fixture<ComplexClass>()
        assertThat(result::class).isEqualTo(ComplexClass::class)
    }

    @Test
    fun `test 7`() {
        val result = fixture<ComplexClass>()
        println(result)
        assertThat(result::class).isEqualTo(ComplexClass::class)
    }

    @Test
    fun `test 8`() {
        val result = fixture<SampleClass11>()
        println(result)
        assertThat(result::class).isEqualTo(SampleClass11::class)
    }

}

class SampleClass {
    var id: Long = 0
    var name: String = ""

    override fun toString(): String {
        return "${this::class.simpleName}(${this::class.memberProperties.joinToString { "${it.name}=${it.getter.call(this)}" }})"
    }
}

class SampleClass11 {
    var id: Long? = 0
    var name: String? = ""

    override fun toString(): String {
        return "${this::class.simpleName}(${this::class.memberProperties.joinToString { "${it.name}=${it.getter.call(this)}" }})"
    }
}

class SampleClass2 {
    var id: Long = 0
    var name: String = ""
    var time: LocalDateTime = LocalDateTime.now()
}

data class SampleClass3(
        val id: Long,
        val name: String
)

data class SampleClass4(
        val id: Long,
        val name: String,
        val time: LocalDateTime
)

data class SampleClass5(
        val id: Long,
        val name: String,
        val time: Timestamp
)

data class ComplexClass(
        val id: Long,
        val name: String,
        val sample1: SampleClass,
        val sample2: SampleClass2,
        val sample4: SampleClass4,
        val sample5: SampleClass5
)