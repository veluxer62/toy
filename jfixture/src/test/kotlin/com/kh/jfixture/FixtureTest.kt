package com.kh.jfixture

import com.appmattus.kotlinfixture.kotlinFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FixtureTest {

    private val fixture = kotlinFixture()

    @Test
    fun `test data class`() {
        val actual = fixture<SampleDataClass>()

        println(actual)

        assertThat(actual.id).isNotEqualTo(0)
        assertThat(actual.name).isNotEmpty()
        assertThat(actual.type).isNotNull()
        assertThat(actual.list).isNotEmpty
    }

    @Test
    @AutoData
    fun `test late init data class`(data: SampleLateInitDataClass) {
        // Arrange
        val actual = fixture<SampleLateInitDataClass>()

        println(actual)
        println(actual.sampleDataClass)

        assertThat(actual.id).isNotEqualTo(0)
        assertThat(actual.sampleDataClass.id).isNotEqualTo(0)
        assertThat(actual.sampleDataClass.name).isNotEmpty()
        assertThat(actual.sampleDataClass.type).isNotNull()
        assertThat(actual.sampleDataClass.list).isNotEmpty
    }

}

