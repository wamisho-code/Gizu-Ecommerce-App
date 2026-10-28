package com.example.ecomerceapp.uistate

import com.example.ecomerceapp.model.product_models.ProductModel

data class ProductListingUiState(
    val products: List<ProductModel> =  emptyList(),
    val isLoading: Boolean= false,
    val errorMessage:String? = null
)
