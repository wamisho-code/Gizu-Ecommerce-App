package com.example.ecomerceapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.model.input_validation_models.PostProductFirstPartValidationResult
import com.example.ecomerceapp.model.input_validation_models.PostProductThirdPartValidationResult
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.utility.BasicDataCheckUtility
import com.example.ecomerceapp.utility.ToastUtility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostProductViewModel: ViewModel() {
    private val  _uiState= MutableStateFlow(PostProductUiState())
    private val _isImageSelected= MutableStateFlow(true)
    val isImageSelected= _isImageSelected.asStateFlow()

    private val _inputStatusForFirstPart = MutableStateFlow(
        PostProductFirstPartValidationResult(
            true,true,true,false
        )
    )
    val inputStatusForFirstPart = _inputStatusForFirstPart.asStateFlow()

    private val _inputStatusForThirdPart = MutableStateFlow(
        PostProductThirdPartValidationResult(
            true,true,true
        )
    )
    val inputStatusForThirdPart = _inputStatusForThirdPart.asStateFlow()

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

    fun setCategory(category: String) {
        _uiState.update {
            it.copy(category = category)
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
        _isImageSelected.value=_uiState.value.selectedImages.isNotEmpty()

    }
    fun removeImage(uri:Uri){
        _uiState.update {
            it.copy(
                selectedImages = it.selectedImages - uri
            )
        }
        _isImageSelected.value=_uiState.value.selectedImages.isNotEmpty()
    }
    fun setPrice(price: String,context: Context){
        val p= price.trim()
        if(p=="0")
            ToastUtility.makeLongToast(context,"Quantity can't be zero")
        _uiState.update {
            it.copy(
                price = p
            )
        }
    }
    fun setCondition(condition:String){
        _uiState.update {
            it.copy(condition = condition)
        }
    }
    fun setQuantity(quantity:String, context: Context){
        val quan=quantity.trim()
        if(quan=="0")
            ToastUtility.makeLongToast(context,"Quantity can't be zero")
            _uiState.update {
                it.copy(quantity =quan)
            }
    }
    fun changeErrorStatusForFirstPart(postProductUiState: PostProductUiState): Boolean{
        _inputStatusForFirstPart.value= BasicDataCheckUtility
            .checkForPostProductUiStateFirstPart(postProductUiState)
        return _inputStatusForFirstPart.value.areAllValid
    }
    fun  changeErrorStatusForSecondPart(postProductUiState: PostProductUiState): Boolean{
        _isImageSelected.value =!postProductUiState.selectedImages.isEmpty()
        return _isImageSelected.value
    }

    fun changeErrorStatusForThirdPart(postProductUiState: PostProductUiState): Boolean{
        _inputStatusForThirdPart.value= BasicDataCheckUtility
            .checkForPostProductUiStateThirdPart(postProductUiState)
        return _inputStatusForThirdPart.value.areAllValid
    }
    fun showProgress(){
        _uiState.update {
            it.copy(isLoading = true)
        }
    }
    fun hideProgressBar(){
        _uiState.update {
            it.copy(isLoading = false)
        }
    }



}