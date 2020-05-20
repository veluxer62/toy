package com.example.demo

import java.io.Serializable
import java.time.LocalDateTime

data class Message(
        val body: String = "",
        val time: String = LocalDateTime.now().toString()
) : Serializable