package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun personRoute(handler: PersonHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/persons", handler::create)
            PUT("/persons/{id}", handler::update)
        }
        GET("/persons", handler::findAll)
        GET("/persons/{id}", handler::findById)
        DELETE("/persons/{id}", handler::deleteById)
    }

}