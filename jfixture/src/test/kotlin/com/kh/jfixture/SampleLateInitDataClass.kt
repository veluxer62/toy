package com.kh.jfixture

data class SampleLateInitDataClass(
        val id: Long
) {
    lateinit var sampleDataClass: SampleDataClass
}