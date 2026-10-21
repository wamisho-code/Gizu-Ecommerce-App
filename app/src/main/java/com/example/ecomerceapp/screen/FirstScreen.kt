package com.example.ecomerceapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth



fun FirstScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val user=  auth.currentUser
    return when {
        user==null->
            navController.navigate("auth")
        !user.isEmailVerified->
            navController.navigate("verify")
        else ->
            navController.navigate("home")

    }


}
