package com.rafael.proffy.utils

import android.util.Patterns

object EmailValidator {

    fun isValid(email: String): Boolean {
        val trimmedEmail = email.trim()
        return trimmedEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()
    }
}
