package com.example.demo

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class PersonHandler(private val repository: PersonRepository) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val user = request.awaitBody<Person>()
        repository.save(user).awaitSingle()
        return created(URI("/users"))
            .buildAndAwait()
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ok().contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(repository.findAll().asFlow())
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        return try {
            val id = request.pathVariable("id").toLong()
            val body = repository.findById(id).awaitSingle()

            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(body)
        } catch (e: NoSuchElementException) {
            badRequest().buildAndAwait()
        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val isExist = repository.existsById(id).awaitSingle()

        return if (isExist) {
            val person = request.awaitBody<Person>().copy(id = id)
            repository.save(person).awaitSingle()

            ok().buildAndAwait()
        } else {
            badRequest().buildAndAwait()
        }
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val isExist = repository.existsById(id).awaitSingle()

        return if (isExist) {
            repository.deleteById(id).awaitFirstOrNull()
            ok().buildAndAwait()
        } else {
            badRequest().buildAndAwait()
        }
    }
}