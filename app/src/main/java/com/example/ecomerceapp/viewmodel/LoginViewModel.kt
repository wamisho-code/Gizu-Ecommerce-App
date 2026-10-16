package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        LoginUiState()
    )
    val loginUiState= _uiState.asStateFlow()
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