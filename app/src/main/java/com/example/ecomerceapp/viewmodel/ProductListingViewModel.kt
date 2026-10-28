package com.example.ecomerceapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.repository.ProductRepository
import com.example.ecomerceapp.uistate.ProductListingUiState
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductListingViewModel(private val productRepo: ProductRepository
                              = ProductRepository()): ViewModel() {
    private val _listingUiSate= MutableStateFlow(
        ProductListingUiState()
    )
    val uiState =_listingUiSate.asStateFlow()
    private var productsListener: ListenerRegistration? = null
    init {
        fetchProducts()
    }
    private fun fetchProducts(){
        _listingUiSate.update {
            it.copy(isLoading = true)
        }
        productsListener= productRepo.fetchProducts(
            onResult = { products->
                _listingUiSate.value= ProductListingUiState(
                    isLoading = false,
                    products = products
                )
            },
            onError = { message->
                _listingUiSate.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = message
                    )
                }
            }
        )
    }

    @SuppressLint("EmptySuperCall")
    override fun onCleared() {
        productsListener?.remove()
        super.onCleared()
    }


}