package com.luke.app.api.post

import com.luke.app.api.post.repository.PostRepository
import com.luke.app.api.post.service.PostService
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.server.ServerWebInputException

private val log = KotlinLogging.logger {}

@Component
class PostHandler(
        private val postService: PostService,
        private val postRepository: PostRepository
) {

    private val validator = PostValidator()

    suspend fun listPosts(request: ServerRequest): ServerResponse {
        log.info { "]-----] TagHandler::findAll [-----[ call " }
        val sort = Sort.by(Sort.Direction.DESC, "id")
        val page = if (request.queryParam("page").isPresent) request.queryParam("page").get().toInt() else 0
        val size = if (request.queryParam("size").isPresent) request.queryParam("size").get().toInt() else 20
        return postService.findAllBySearch(PageRequest.of(page, size, sort), request.queryParams()).let {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    suspend fun createPost(request: ServerRequest): ServerResponse {
        val post = request.awaitBody<Post>()
        validate(post)
        postRepository.save(post)
        return ok().buildAndAwait()
    }


    suspend fun getPost(request: ServerRequest): ServerResponse {
        log.info { "]-----] TagHandler::findAll [-----[ call " }

        val postId = request.pathVariable("id").toLong()
        val sort = Sort.by(Sort.Direction.DESC, "id")
        val page = if (request.queryParam("page").isPresent) request.queryParam("page").get().toInt() else 0
        val size = if (request.queryParam("size").isPresent) request.queryParam("size").get().toInt() else 20
        return postRepository.findById(postId)?.let {
            ok().contentType(APPLICATION_JSON).bodyValueAndAwait(it)
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun hello(request: ServerRequest): ServerResponse {
        return ok().contentType(APPLICATION_JSON).bodyValueAndAwait("hello")
    }


    fun entityToView(post: Post): PostView {
        val postView = PostView()
        postView.getView(post)
        return postView
    }

    private fun validate(post: Post) {
        val errors: Errors = BeanPropertyBindingResult(post, "post");
        validator.validate(post, errors);
        if (errors.hasErrors()) {
            throw ServerWebInputException(errors.toString())
        }
    }


}