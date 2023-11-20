package com.luke.app.api.test


import com.luke.app.api.common.model.BaseView

class TestView : BaseView() {

    var title = ""
    var contents = ""

    fun getView(test: Test) {
        this.title = test.title
        this.contents = test.contents
    }

}
