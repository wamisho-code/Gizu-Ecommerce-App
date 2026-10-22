package com.example.ecomerceapp.repository

import com.example.ecomerceapp.model.UserModel
import com.example.ecomerceapp.uistate.LoginUiState
import com.example.ecomerceapp.uistate.SignUpUiState
import com.example.ecomerceapp.utility.ToastUtility
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private  val fireStore= FirebaseFirestore.getInstance()

    fun login(email:String,password:String,onResult:(Boolean,String?)->Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    onResult(true,null);
                }else{
                    onResult(false,null);
                }
            }
    }
    fun signUp(email: String,password: String, onResult:(Boolean, String?)->Unit){
        val user = auth.currentUser
        if(user==null){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    verifyEmail(onResult)
                }else{
                    onResult(false,it.exception?.localizedMessage)
                }

            }}
        else{
            if(user.email==email && !user.isEmailVerified){
                onResult(false,"exiteNotVerified")
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
                    onResult(false,it.exception?.localizedMessage)
                }
            }
    }
    fun checkEmailIsVerified(fullName: String?, phoneNumber: String?,
                             onResult: (Boolean, String?) -> Unit) {

        val user = auth.currentUser

        if (user == null) {
            onResult(false, "User is not logged in")
            return
        }

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
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    email = currentUser.email,
                    userId = userId
                )

                fireStore.collection("users")
                    .document(userId)
                    .set(userModel)
                    .addOnCompleteListener { firestoreTask ->

                        if (firestoreTask.isSuccessful) {

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
    fun logout(){
        auth.signOut()
    }

}