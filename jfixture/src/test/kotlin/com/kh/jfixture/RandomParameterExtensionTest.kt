package com.kh.jfixture

import com.kh.jfixture.RandomParameterExtension.Random
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class RandomParameterExtensionTest {
    @Test
    @ExtendWith(RandomParameterExtension::class)
    fun `test`(@Random sample: SampleDataClass, @Random sample2: SampleLateInitDataClass) {
        // Arrange
        println(sample)
        println(sample2)
        println(sample2.sampleDataClass)

        Assertions.assertThat(sample.id).isNotEqualTo(0)
        Assertions.assertThat(sample.name).isNotEmpty()
        Assertions.assertThat(sample.type).isNotNull()
        Assertions.assertThat(sample.list).isNotEmpty
    }
}
