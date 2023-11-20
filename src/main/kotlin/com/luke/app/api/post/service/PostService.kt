package com.luke.app.api.post.service

import com.luke.app.api.post.PostRes
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.util.MultiValueMap

interface PostService {
    fun findAllBySearch(pageable: Pageable, queryParams: MultiValueMap<String, String>): Page<PostRes>
}