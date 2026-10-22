package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.model.UserModel
import com.example.ecomerceapp.repository.AuthRepository
import com.example.ecomerceapp.uistate.LoginUiState
import com.example.ecomerceapp.uistate.SignUpUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel(private val authRepository:
                              AuthRepository= AuthRepository()): ViewModel() {
    private var _isEmailVerified = MutableStateFlow(false)
    val isEmailVerified= _isEmailVerified.asStateFlow()

    private var signUpUiState: SignUpUiState?=null;

    fun login(loginUiState: LoginUiState,onResult:(Boolean,String?)->Unit){
            authRepository.login(loginUiState.email,loginUiState.password,onResult)
    }
    fun signUp(signUpUiState: SignUpUiState,onResult:(Boolean,String?)->Unit){
        this.signUpUiState=signUpUiState
        authRepository.signUp(signUpUiState.email,signUpUiState.password,onResult)
    }
    fun verifyEmail(onResult:(Boolean,String?)->Unit){
        authRepository.verifyEmail(onResult)
    }

    fun checkEmailIsVerified(onResult: (Boolean, String?) -> Unit) {

        if (_isEmailVerified.value ) return

        authRepository.checkEmailIsVerified(signUpUiState?.fullName,
            signUpUiState?.phoneNumber
        ){ bool, message->
                if(bool==true){
                    _isEmailVerified.value = true
                }
        }
    }

}