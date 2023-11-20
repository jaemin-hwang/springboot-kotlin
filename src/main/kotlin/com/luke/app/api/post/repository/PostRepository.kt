package com.luke.app.api.post.repository


import com.luke.app.api.common.support.ActiveRepository
import com.luke.app.api.post.Post

interface PostRepository : ActiveRepository<Post, Long> {

}
