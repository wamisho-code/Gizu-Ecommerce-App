package com.example.ecomerceapp.model.input_validation_models

data class LoginValidationResult(
    val isPasswordStrong: Boolean,
    val isValid: Boolean,
    val message: String? = null
)