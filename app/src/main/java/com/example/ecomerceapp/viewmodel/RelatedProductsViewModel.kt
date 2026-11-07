package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.repository.ProductRepository
import com.example.ecomerceapp.uistate.RelatedProductUiSate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RelatedProductsViewModel(private  val productRepo:
                               ProductRepository= ProductRepository())
    : ViewModel() {
    private val _uiState = MutableStateFlow(
        RelatedProductUiSate()
    )
    val relatedProductsUiState = _uiState.asStateFlow()


     fun getRelatedProducts(product: ProductModel){

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                val relatedProduct =productRepo.getRelatedProducts(product = product)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        relatedProducts = relatedProduct
                    )
                }
            }catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage
                    )
                }
            }
        }
    }
}