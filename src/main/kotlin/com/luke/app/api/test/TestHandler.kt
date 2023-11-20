package com.luke.app.api.test

import com.luke.app.api.test.service.TestService
import com.luke.app.api.test.validator.TestValidator
import com.luke.app.api.test.view.TestReq
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

private val log = KotlinLogging.logger {}

@Component
class TestHandler(
        private val testService: TestService,
) {

    private val validator = TestValidator()

    suspend fun listTests(request: ServerRequest): ServerResponse {
        log.info { "]-----] TestHandler::listTests [-----[ call " }
        val sort = Sort.by(Sort.Direction.DESC, "id")
        val page = if (request.queryParam("page").isPresent) request.queryParam("page").get().toInt() else 0
        val size = if (request.queryParam("size").isPresent) request.queryParam("size").get().toInt() else 20
        return testService.findAllBySearch(PageRequest.of(page, size, sort), request.queryParams()).let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    suspend fun createTest(request: ServerRequest): ServerResponse {
        log.info { "]-----] TestHandler::createTest [-----[ call " }
        val testReq = request.awaitBody<TestReq>()
        return testService.create(testReq).let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }



}