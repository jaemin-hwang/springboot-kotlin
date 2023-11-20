package com.luke.app.api.post


import com.luke.app.api.common.model.BaseView

class PostView : BaseView() {

    var title = ""
    var content = ""

    fun getView(post: Post) {
        this.title = post.title
        this.content = post.content
    }

}
