package com.example.ecomerceapp.uistate

import android.net.Uri

data class PostProductUiState(
    val name:String="",
    val description: String="",
    val category:String="",
    val attributes: Map<String,String> = emptyMap(),
    val attributeKey: String= "",
    val attributeValue: String="",
    val selectedImages: List<Uri> = emptyList(),
    val price: String="",
    val quantity: String="",
    val condition: String= "NEW",
    val isLoading: Boolean =false

)
