package com.example.demo

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/persons"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping
    fun findAll() =
        ResponseEntity.ok(personRepository.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Person> =
        personRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.badRequest().build())

    @PostMapping
    fun create(@RequestBody person: Person) =
        personRepository.save(person)

    @PutMapping("/{id}")
    fun update(
        @RequestBody person: Person,
        @PathVariable("id") id: Long
    ): ResponseEntity<Person> {
        val exist = personRepository.existsById(id)
        return if (exist)
            ResponseEntity.ok(personRepository.save(person.copy(id = id)))
        else
            ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val exist = personRepository.existsById(id)
        return if (exist)
            ResponseEntity.ok(personRepository.deleteById(id))
        else
            ResponseEntity.badRequest().build()
    }


}