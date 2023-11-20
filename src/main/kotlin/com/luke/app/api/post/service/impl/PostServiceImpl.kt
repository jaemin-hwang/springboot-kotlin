package com.luke.app.api.post.service.impl

import com.luke.app.api.post.repository.PostRepository
import com.luke.app.api.post.PostRes
import com.luke.app.api.post.render
import com.luke.app.api.post.service.PostService
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MultiValueMap

private val log = KotlinLogging.logger {}

@Service
class PostServiceImpl(
        private val postRepository: PostRepository
) : PostService {

    @Transactional(readOnly = true)
    override fun findAllBySearch(pageable: Pageable, queryParams: MultiValueMap<String, String>): Page<PostRes> {
        log.debug { "]-----] ArtistAdminServiceImpl::findAll [-----[ call " }
        return postRepository.findAllByActive(true, pageable).map { it.render() }
    }
}