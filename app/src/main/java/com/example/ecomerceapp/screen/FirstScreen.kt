package com.example.ecomerceapp.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.ecomerceapp.utility.ToggleSystemBars
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FirstScreen(navController: NavController) {
    ToggleSystemBars(false)
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    LaunchedEffect(Unit) {

        when {
            user == null -> {
                navController.navigate("auth") {
                    popUpTo("first_screen") {
                        inclusive = true
                    }
                }
            }

            !user.isEmailVerified -> {
                navController.navigate("verify-email") {
                    popUpTo("first_screen") {
                        inclusive = true
                    }
                }
            }

            else -> {
                navController.navigate("home") {
                    popUpTo("first_screen") {
                        inclusive = true
                    }
                }
            }
        }
    }
}