package com.luke.app.api.test.service.impl

import com.luke.app.api.test.Test
import com.luke.app.api.test.repository.TestRepository
import com.luke.app.api.test.view.TestRes
import com.luke.app.api.test.render
import com.luke.app.api.test.service.TestService
import com.luke.app.api.test.view.TestReq
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MultiValueMap

private val log = KotlinLogging.logger {}

@Service
class TestServiceImpl(
        private val postRepository: TestRepository
) : TestService {

    @Transactional(readOnly = true)
    override fun findAllBySearch(pageable: Pageable, queryParams: MultiValueMap<String, String>): Page<TestRes> {
        log.debug { "]-----] ArtistAdminServiceImpl::findAll [-----[ call " }
        return postRepository.findAllByActive(true, pageable).map { it.render() }
    }

    @Transactional
    override fun create(postReq: TestReq): TestRes {

        val newTest = Test(
                title = postReq.title,
                contents = postReq.contents,
        )

        postRepository.save(newTest)

        return TestRes(newTest.id!!)
    }
}