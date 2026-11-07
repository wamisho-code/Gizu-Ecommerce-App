package com.example.ecomerceapp.uistate

import com.example.ecomerceapp.model.product_models.ProductModel

data class RelatedProductUiSate(
    val relatedProducts: List<ProductModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? =  null
)
