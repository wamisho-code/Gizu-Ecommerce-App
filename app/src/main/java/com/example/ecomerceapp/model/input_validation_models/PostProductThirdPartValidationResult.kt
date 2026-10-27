package com.example.ecomerceapp.model.input_validation_models

data class PostProductThirdPartValidationResult(
    val isPriceValid: Boolean,
    val isQuantityValid: Boolean,
    val areAllValid: Boolean
)
