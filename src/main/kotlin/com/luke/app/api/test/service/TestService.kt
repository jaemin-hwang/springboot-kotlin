package com.luke.app.api.test.service

import com.luke.app.api.test.view.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.util.MultiValueMap

interface TestService {
    fun findAllBySearch(pageable: Pageable, queryParams: MultiValueMap<String, String>): Page<TestRes>
    fun create(postReq: TestReq) : TestRes
}