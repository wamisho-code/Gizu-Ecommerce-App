package com.example.ecomerceapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.utility.ToastUtility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostProductViewModel: ViewModel() {
    private val  _uiState= MutableStateFlow(PostProductUiState())
    val uiState= _uiState.asStateFlow()
    fun setProductName(name:String){
        _uiState.update {
            it.copy(name = name)
        }
    }
    fun setDescription(desc:String){
        _uiState.update {
            it.copy(description = desc)
        }
    }
    fun setAttributeKey(key:String){
        _uiState.update {
            it.copy(attributeKey = key)
        }
    }

    fun setAttributeValue(value: String) {
        _uiState.update {
            it.copy(attributeValue = value)
        }
    }

    fun addAttribute() {
        _uiState.update { state ->

            if (
                state.attributeKey.isBlank() ||
                state.attributeValue.isBlank() ||
                state.attributes.size >= 5
            ) {
                return@update state
            }

            state.copy(
                attributes = state.attributes + (
                        state.attributeKey.trim() to state.attributeValue.trim()
                        ),
                attributeKey = "",
                attributeValue = ""
            )
        }
    }
    fun removeAttribute(key:String){
        _uiState.update {
            it.copy(attributes=it.attributes.toMutableMap().apply {
                remove(key)
            })
        }
    }
    //update the already displayed attributes when key changes
    fun updateAttributeOldKey(oldKey: String, newKey: String, value: String) {
        _uiState.update { state ->
            val updatedAttributes = state.attributes
                .toList()
                .map { (key, oldValue) ->
                    if (key == oldKey) newKey to value
                    else key to oldValue
                }
                .toMap()

            state.copy(attributes = updatedAttributes)
        }
    }
    //update the already displayed attributes when value changes
    fun updateAttributeOldValue( newValue: String, key: String) {
        _uiState.update { state ->
            val updatedAttributes = state.attributes
                .toList()
                .map { (ikey, value) ->
                    if (ikey == key) ikey to newValue
                    else ikey to value
                }
                .toMap()

            state.copy(attributes = updatedAttributes)
        }
    }
    fun setImages(uris: List<Uri>){
        //only holds five images
        _uiState.update { state ->
            val remainingSlot= 5 - state.selectedImages.size
            val newImages= uris.take(remainingSlot)
            state.copy(
                selectedImages = state.selectedImages + newImages
            )
        }

    }
    fun removeImage(uri:Uri){
        _uiState.update {
            it.copy(
                selectedImages = it.selectedImages - uri
            )
        }
    }
    fun setPrice(price: Double){
        _uiState.update {
            it.copy(
                price = price
            )
        }
    }
    fun setCondition(condition:String){
        _uiState.update {
            it.copy(condition = condition)
        }
    }
    fun setQuantity(quantity:String,context: Context){
        if(quantity.isEmpty()){
            _uiState.update {
                it.copy(quantity = 1)
            }
        }else{
        try {
            val quan= if(quantity.toInt()>0)quantity.toInt() else 1
            _uiState.update {
                it.copy(quantity = quan)
            }
        }catch (e: Exception){
            _uiState.update {
                it.copy(quantity = 1)
            }
            ToastUtility.makeLongToast(context,"Insert Valid Quantity value")
        }
        }

    }


}