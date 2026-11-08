package com.example.ecomerceapp.utility

import com.example.ecomerceapp.local_database.entity.CartEntity
import com.example.ecomerceapp.model.product_models.ProductImageModel
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.repository.CloudinaryRepository
import com.example.ecomerceapp.uistate.PostProductUiState

class DataMappingUtility() {
    companion object{
        fun mapProduct(productUiState: PostProductUiState,images:List<ProductImageModel>): ProductModel{
            return ProductModel(
                productName = productUiState.name,
                productDescription = productUiState.description,
                attributes = productUiState.attributes,
                productImages =images,

            )

        }
        fun mapCartEntity(product: ProductModel): CartEntity {
            val cart = CartEntity()

            cart.productId = product.productId
            cart.productName = product.productName
            cart.productImage = product.productImages.firstOrNull()?.url ?: ""
            cart.price = product.price

            return cart
        }
    }
}