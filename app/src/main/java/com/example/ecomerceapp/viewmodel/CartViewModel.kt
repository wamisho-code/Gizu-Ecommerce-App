package com.example.ecomerceapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecomerceapp.local_database.entity.CartEntity
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.repository.CartRepository
import com.example.ecomerceapp.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(application: Application,
    ): AndroidViewModel(application) {
    private val productRepo: ProductRepository = ProductRepository()

    private val repo = CartRepository(application.applicationContext)
    private val _isLoading = MutableStateFlow(false)
    val isLoading= _isLoading.asStateFlow()
    val cartItems: LiveData<List<CartEntity>> = repo.cartItems

    fun addToCart(cart: CartEntity){
        repo.insertCart(cart)
    }

    fun deleteFromCart(id: Int){
        repo.deleteCart(id)
    }
    fun updateCart(cart: CartEntity){
        repo.updateCart(cart)
    }
    fun getProduct(id: String, onResult2:(ProductModel?)->Unit){
        viewModelScope.launch {
            _isLoading.value= true
            productRepo.fetchProduct(id,
                onResult = { product->
                    _isLoading.value=false
                    onResult2(product)
                },
                onError = {
                    _isLoading.value=false
                    onResult2(null)
                }
            )
        }

    }
    fun isAddedToCart(productId:String): LiveData<Boolean>{
        return  repo.isAddedToCart(productId)
    }
    fun fetchCart(productId:String,onResult:(CartEntity?)->Unit){
        repo.fetchCart(productId){ product->
            onResult(product)
        }
    }
}