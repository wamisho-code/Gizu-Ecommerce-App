package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.uistate.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel: ViewModel() {
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
}