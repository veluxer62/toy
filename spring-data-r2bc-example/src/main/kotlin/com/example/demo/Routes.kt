package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routes {
    @Bean
    fun personRoutes(handler: PersonHandler) = coRouter {
        "/persons".nest {
            POST("", handler::create)
            GET("", handler::findAll)
            GET("/{id}", handler::findById)
            PUT("/{id}", handler::update)
            DELETE("/{id}", handler::delete)
        }
    }
}