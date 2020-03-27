package com.example.demo

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class PersonHandler(private val repository: PersonRepository) {
    suspend fun create(request: ServerRequest) =
        repository
            .save(request.awaitBody<Person>())
            .awaitSingle()
            .let {
                created(URI("/users"))
                    .buildAndAwait()
            }

    suspend fun findAll(request: ServerRequest) =
        ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(repository.findAll().asFlow())

    suspend fun findById(request: ServerRequest) =
        repository
            .findById(request.pathVariable("id").toLong())
            .awaitFirstOrNull()
            ?.let {
                ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(it)
            }
            ?: badRequest().buildAndAwait()

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