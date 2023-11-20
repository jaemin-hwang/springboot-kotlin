package com.luke.app.api.post


import com.luke.app.api.common.model.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty
import java.util.*

@Entity
@Table(name = "posts")
class Post(
        var title: String,
        var content: String
) : BaseEntity()


fun Post.render() = PostRes(
        id = id!!,
        title = title,
        content = content
)