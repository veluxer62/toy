package com.example.demo

import kotlinx.coroutines.reactive.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class PersonHandler(
    private val repository: PersonRepository
) {

    suspend fun create(request: ServerRequest) =
        repository
            .save(request.awaitBody<Person>())
            .awaitSingle().let {
                created(URI("/person/${it.id}"))
                    .buildAndAwait()
            }

    suspend fun update(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val exist = repository.existsById(id).awaitSingle()
        return if (exist) {
            val body = request.awaitBody<Person>().copy(id = id)
            repository.save(body).awaitSingle().let {
                ok().buildAndAwait()
            }
        } else {
            badRequest().buildAndAwait()
        }
    }

    suspend fun findAll(request: ServerRequest) =
        ok().bodyAndAwait(repository.findAll().asFlow())

    suspend fun findById(request: ServerRequest) =
        try {
            ok().bodyValueAndAwait(
                repository
                    .findById(request.pathVariable("id"))
                    .awaitSingle()
            )
        } catch (e: Exception) {
            badRequest().buildAndAwait()
        }

    suspend fun deleteById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val exist = repository.existsById(id).awaitSingle()
        return if (exist) {
            repository.deleteById(id).awaitFirstOrNull()
            ok().buildAndAwait()
        } else {
            badRequest().buildAndAwait()
        }
    }
}