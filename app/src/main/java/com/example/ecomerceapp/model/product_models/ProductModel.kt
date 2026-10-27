package com.example.ecomerceapp.model.product_models

import com.example.ecomerceapp.model.product_models.enums.ProductCondition
import com.google.firebase.Timestamp

data class ProductModel(
    val productId:String= "",
    val sellerId:String="",
    val productName:String="",
    val productDescription: String="",
    val attributes: Map<String,String> = emptyMap(),
    val productImages: List<ProductImageModel> = emptyList(),
    val price: Double=0.0,
    val quantity: Int=1,
    val condition: ProductCondition = ProductCondition.NEW,
    val createdAt: Timestamp? = null
)