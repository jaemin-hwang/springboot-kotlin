package com.luke.app.api.test.repository


import com.luke.app.api.common.support.ActiveRepository
import com.luke.app.api.test.Test

interface TestRepository : ActiveRepository<Test, Long> {

}
