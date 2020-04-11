package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody person: Person) {
        repository.save(person).subscribe()
    }

    @GetMapping
    fun findAll(): Flux<Person> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): Mono<Person> {
        return repository.findById(id)
            .switchIfEmpty {
                throw IllegalArgumentException()
            }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody person: Person) {
        repository.existsById(id)
            .map {
                if (!it) throw IllegalArgumentException()
            }
            .subscribe(
                { repository.save(person.copy(id = id)) },
                { throw IllegalArgumentException(it) }
            )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        repository.existsById(id)
            .map {
                if (!it) throw IllegalArgumentException()
            }
            .subscribe(
                { repository.deleteById(id) },
                { throw IllegalArgumentException(it) }
            )
    }

}
