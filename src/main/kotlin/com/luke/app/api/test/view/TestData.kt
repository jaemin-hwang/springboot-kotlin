package com.luke.app.api.test.view

data class TestRes(
        var id : Long
)

data class TestReq(
        var title : String,
        var contents : String
)