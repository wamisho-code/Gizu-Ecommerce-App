package com.example.ecomerceapp.model.input_validation_models

data class PostProductFirstPartValidationResult(
    val isNameValid: Boolean,
    val isDescriptionValid: Boolean,
    val isCategorySelected: Boolean,
    val areAllValid: Boolean

)