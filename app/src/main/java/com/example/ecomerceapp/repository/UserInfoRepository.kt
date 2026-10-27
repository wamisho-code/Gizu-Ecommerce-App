package com.example.ecomerceapp.repository

import com.example.ecomerceapp.model.user_models.UserDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserInfoRepository {
    private val auth= FirebaseAuth.getInstance()
    private val fireStore= FirebaseFirestore.getInstance()
    fun getUserInfo(onResult:(Boolean, UserDto?,String?)->Unit){
        val user= auth.currentUser

        val id= user?.uid
        println("id: ${id}")
        if(id!=null){
            fireStore.collection("users")
                .document(id)
                .get()
                .addOnSuccessListener { document->
                    if (document.exists()){
                        val userDto= document.toObject(UserDto::class.java)
                        println("sc: User:${userDto?.fullName}")

                        onResult(true,userDto,null)
                    }else{
                        println("er: User Not found")
                        onResult(false,null,"User Not found")
                    }
                }.addOnFailureListener { exception ->
                    println("er2: ${exception.message}")
                    onResult(false,null,exception.message)
                }
        }
    }
}