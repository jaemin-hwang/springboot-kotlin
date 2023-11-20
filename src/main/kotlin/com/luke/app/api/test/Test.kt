package com.luke.app.api.test


import com.luke.app.api.common.model.BaseEntity
import com.luke.app.api.test.view.TestRes
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "tests")
class Test(
        var title: String,
        var contents: String
) : BaseEntity()


fun Test.render() = TestRes(
        id = id!!
)

fun Test.renderList() = TestRes(
        id = id!!
)