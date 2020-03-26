package com.example.demo

import org.springframework.data.annotation.Id

data class Person(
    @Id val id: Long?,
    val firstname: String,
    val lastname: String
)