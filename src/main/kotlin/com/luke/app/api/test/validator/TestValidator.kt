package com.luke.app.api.test.validator

import com.luke.app.api.test.Test
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator


class TestValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return Test::class.java == clazz
    }

    override fun validate(obj: Any, e: Errors) {
        ValidationUtils.rejectIfEmpty(e, "title", "title.empty")
    }
}