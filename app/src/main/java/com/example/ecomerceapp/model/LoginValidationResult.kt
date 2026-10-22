package com.example.ecomerceapp.model

data class LoginValidationResult(
    val isPasswordStrong: Boolean,
    val isValid: Boolean,
    val message: String? = null
)
