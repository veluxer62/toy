package com.kh.jfixture

import com.appmattus.kotlinfixture.kotlinFixture
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class RandomParameterExtension : ParameterResolver {

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.VALUE_PARAMETER)
    annotation class Random

    private val fixture = kotlinFixture()

    override fun supportsParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Boolean {
        return parameterContext?.isAnnotated(Random::class.java) ?: false
    }

    override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Any {
        return fixture.create(parameterContext!!.parameter.type)!!
    }
}