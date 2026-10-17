package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.uistate.SignUpUiState
import com.example.ecomerceapp.utility.BasicDataCheckUtility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpUIViewModel: ViewModel() {
    private val _isPasswordStrong = MutableStateFlow(false)
    val isPasswordStrong=_isPasswordStrong.asStateFlow()

    private val _areAllInputsValid = MutableStateFlow(false)
    val areAllInputsValid=_isPasswordStrong.asStateFlow()

    private val _isPhoneValid = MutableStateFlow(false)
    val isPhoneValid=_isPasswordStrong.asStateFlow()

    private val _uiState = MutableStateFlow(
        SignUpUiState()
    )
    var signUpUiState= _uiState.asStateFlow()

    fun setFulName(fullName:String){
        _uiState.update {
            it.copy(fullName=fullName)
        }
    }
    fun setPhone(phoneNumber:String){
        _uiState.update {
            it.copy(phoneNumber = phoneNumber)
        }
    }
    fun setEmail(email:String){
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun setPassword(password:String){
        _uiState.update {
            it.copy(password = password)
        }
    }
    fun setIsLoadingTrue(){
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    fun setIsLoadingFasle(){
        _uiState.update {
            it.copy(isLoading = false)
        }
    }


    fun validateSignUp():String?{
        val validatedResult=  BasicDataCheckUtility.checkForSignUpUIState(signUpUiState.value)
        _isPhoneValid.value= validatedResult.isPhoneValid
        _isPasswordStrong.value= validatedResult.isPasswordStrong
        _areAllInputsValid.value=validatedResult.isValid
        return validatedResult.message
    }
}