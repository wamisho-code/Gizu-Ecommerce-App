package com.example.ecomerceapp.model

data class SignUpValidationResult(
    val isPhoneValid: Boolean,
    val isPasswordStrong: Boolean,
    val isValid: Boolean,
    val message: String? = null
)