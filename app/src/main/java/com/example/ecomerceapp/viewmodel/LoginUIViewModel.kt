package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.uistate.LoginUiState
import com.example.ecomerceapp.utility.BasicDataCheckUtility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginUIViewModel: ViewModel() {
    private val _passwordStrength = MutableStateFlow(true)
    val isPasswordStrong =_passwordStrength.asStateFlow()


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
    fun isLoadingTrue(){
        _uiState.update {
            it.copy(isLoading = true)
        }
    }
    fun isLoadingFalse(){
        _uiState.update {
            it.copy(isLoading = false)
        }
    }
    fun validateInput(loginUiState: LoginUiState): String?{
        val result= BasicDataCheckUtility.checkForLoginUiStateInput(loginUiState)
        _passwordStrength.value=result.isPasswordStrong
        return result.message
    }
}