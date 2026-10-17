package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.model.UserModel
import com.example.ecomerceapp.uistate.SignUpUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel(): ViewModel() {
    private var _isEmailVerified = MutableStateFlow(false)
    val isEmailVerified= _isEmailVerified.asStateFlow()
    private var _showVerify = MutableStateFlow(false)
    val showVerify= _showVerify.asStateFlow()
    private val auth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()
    private var signUpUiState: SignUpUiState?=null;

    fun login(){}
    fun signUp(signUpUiState: SignUpUiState,onResult:(Boolean,String?)->Unit){
        this.signUpUiState=signUpUiState
        auth.createUserWithEmailAndPassword(signUpUiState.email,signUpUiState.password)
            .addOnCompleteListener { signUpUiState
                if(it.isSuccessful){
                    verifyEmail(onResult)
                }else{
                    onResult(false,it.exception?.localizedMessage)
                }

            }
    }
    fun verifyEmail(onResult:(Boolean,String?)->Unit){
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener {
                if(it.isSuccessful){
                    onResult(true,null)
                }else{
                    _showVerify.value=false;
                    onResult(false,it.exception?.localizedMessage)
                }
            }
    }
    fun checkEmailIsVerified(onResult:(Boolean,String?)->Unit){
        val user= auth.currentUser
        if(user==null){
            onResult(false,"User is not logged in")
            return
        }

        user.reload()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val isVerified= user.isEmailVerified
                    _isEmailVerified.value= isVerified
                    if(isVerified==true){
                        val userId= user.uid
                        val userModel= UserModel(
                            fullName = signUpUiState?.fullName,
                            phoneNumber = signUpUiState?.phoneNumber,
                            email = signUpUiState?.email,
                            userId = userId
                        )
                        fireStore.collection("users")
                            .document(userId)
                            .set(userModel)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    onResult(true,null)
                                }else
                                    onResult(false,it.exception?.localizedMessage)
                            }
                    }else{
                    onResult(false,it.exception?.localizedMessage)}
                }else{
                    onResult(false,it.exception?.localizedMessage)
                }
            }
    }

}