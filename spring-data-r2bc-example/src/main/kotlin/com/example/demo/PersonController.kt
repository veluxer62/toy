package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonRepository) {

    @PostMapping
    fun create(@RequestBody requestBody: Mono<Person>): Mono<ResponseEntity<Unit>> {
        return requestBody
            .flatMap { repository.save(it) }
            .map { ResponseEntity.created(URI.create("/persons")).build<Unit>() }
    }

    @GetMapping
    fun findAll(): Flux<Person> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): Mono<ResponseEntity<Person>> {
        return repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.badRequest().build())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody requestBody: Mono<Person>): Mono<ResponseEntity<Unit>> {
        return repository.existsById(id)
            .zipWith(requestBody)
            .flatMap {
                if (it.t1)
                    Mono.just(it.t2.copy(id = id))
                else
                    Mono.empty()
            }
            .flatMap { repository.save(it) }
            .map { ResponseEntity.ok().build<Unit>() }
            .defaultIfEmpty(ResponseEntity.badRequest().build())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): Mono<ResponseEntity<Unit>> {
        return repository.existsById(id)
            .flatMap {
                if (it)
                    repository.deleteById(id)
                        .then(Mono.just(ResponseEntity.ok().build<Unit>()))
                else
                    Mono.just(ResponseEntity.badRequest().build())
            }
    }

}
