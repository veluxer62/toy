package com.kh.jfixture

data class SampleDataClass(
        val id: Long,
        val name: String,
        val type: SampleType,
        val list: List<Map<String, Any>>
)