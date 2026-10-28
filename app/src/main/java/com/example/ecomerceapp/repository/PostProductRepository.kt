package com.example.ecomerceapp.repository

import com.example.ecomerceapp.model.product_models.ProductImageModel
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.model.product_models.enums.ProductCondition
import com.example.ecomerceapp.uistate.PostProductUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class PostProductRepository(private  val cloudinaryRepository:
                            CloudinaryRepository= CloudinaryRepository()) {
    private  val auth = FirebaseAuth.getInstance()
    private  val fireStore= FirebaseFirestore.getInstance()
    suspend fun postProduct(productUiState: PostProductUiState,
                            onResult:(Boolean,String?)-> Unit){
        try {
            val userId = auth.currentUser?.uid ?:
            throw Exception("Please Login to post product")
            val productRef = fireStore.collection("products")
                .document()
            val imagesUrl = cloudinaryRepository.uploadImages(productUiState.selectedImages)
            if(imagesUrl.isEmpty())
                throw Exception("No image was uploaded")
            postProductToFirestore(
                productUiState=productUiState,
                images = imagesUrl,
                productId = productRef.id,
                userId = userId
            )
            onResult(true,null)
        }catch (e: CancellationException){
            throw  e
        }catch (e: Exception){
            onResult(false,e.message?:"Failed to Post Product")
        }
    }
    private suspend fun postProductToFirestore(productUiState: PostProductUiState,
                               images:List<ProductImageModel>,
                               productId:String,userId:String){
        val product= mapProduct(
            productUiState = productUiState,
            images=images,
            productId=productId,
            userId=userId
        )
        fireStore.collection("products")
            .document(productId)
            .set(
                mapOf(
                    "productId" to product.productId,
                    "sellerId" to product.sellerId,
                    "productName" to product.productName,
                    "productDescription" to product.productDescription,
                    "attributes" to  product.attributes,
                    "productImages" to product.productImages,
                    "price" to product.price,
                    "quantity" to product.quantity,
                    "condition" to product.condition,
                    "createdAt" to FieldValue.serverTimestamp()
                )
            ).await()


    }
    private fun mapProduct(productUiState: PostProductUiState,
                   images:List<ProductImageModel>,
                   productId:String,userId:String): ProductModel{
        val condition = when(productUiState.condition.uppercase()){
            "NEW"-> ProductCondition.NEW
            else -> ProductCondition.USED
        }
        return ProductModel(
            productId=productId,
            sellerId = userId,
            productName = productUiState.name,
            productDescription = productUiState.description,
            attributes = productUiState.attributes,
            productImages =images,
            price = productUiState.price.toDouble(),
            quantity = productUiState.quantity.toInt(),
            condition =condition,
            )

    }
}