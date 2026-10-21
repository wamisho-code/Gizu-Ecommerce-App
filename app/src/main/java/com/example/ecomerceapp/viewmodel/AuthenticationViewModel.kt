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
    fun checkEmailIsVerified(onResult: (Boolean, String?) -> Unit) {

        val user = auth.currentUser

        if (user == null) {
            onResult(false, "User is not logged in")
            return
        }
        if (_isEmailVerified.value ) return

        user.reload()
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    onResult(
                        false,
                        task.exception?.localizedMessage
                    )
                    return@addOnCompleteListener
                }

                val currentUser = auth.currentUser

                if (currentUser?.isEmailVerified != true) {
                    onResult(false, null)
                    return@addOnCompleteListener
                }

                // Email is verified, now save the user in Firestore
                val userId = currentUser.uid

                val userModel = UserModel(
                    fullName = signUpUiState?.fullName,
                    phoneNumber = signUpUiState?.phoneNumber,
                    email = currentUser.email,
                    userId = userId
                )

                fireStore.collection("users")
                    .document(userId)
                    .set(userModel)
                    .addOnCompleteListener { firestoreTask ->

                        if (firestoreTask.isSuccessful) {

                            _isEmailVerified.value = true

                            onResult(true, null)

                        } else {

                            onResult(
                                false,
                                firestoreTask.exception?.localizedMessage
                            )
                        }
                    }
            }
    }

}