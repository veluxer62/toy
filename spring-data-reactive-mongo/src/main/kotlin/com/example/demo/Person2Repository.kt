package com.example.demo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface Person2Repository : ReactiveMongoRepository<Person, String>