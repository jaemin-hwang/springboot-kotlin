package com.luke.app.api.test

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TestRouter {

    @Bean
    fun testRoute(handler: TestHandler) = coRouter {
        accept(APPLICATION_JSON).nest {
            GET("/test/{id}", handler::getTest)
            GET("/test", handler::listTests)
        }
        POST("/test", handler::createTest)
    }
}