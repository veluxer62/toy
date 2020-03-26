package com.example.demo

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface PersonRepository : ReactiveCrudRepository<Person, Long> {
    @Query("select * from person where firstname = :firstname")
    fun findAllByFirstname(firstname: String): Flux<Person>
}