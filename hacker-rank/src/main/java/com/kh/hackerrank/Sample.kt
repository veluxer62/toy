package com.kh.hackerrank

import java.util.*
import kotlin.concurrent.thread

fun main() {
    thread {
        val write = Scanner(System.`in`).nextLine()
        println(write)
    }
    println("Hello, World!")
}