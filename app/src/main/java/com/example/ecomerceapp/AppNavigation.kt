package com.example.ecomerceapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.screen.AuthScreen
import com.example.ecomerceapp.screen.LoginScreen
import com.example.ecomerceapp.screen.SignUpScreen

@Composable
fun  AppNavigation(topPadding: Dp) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "auth") {
        composable("auth"){
            AuthScreen(topPadding,navController)
        }
        composable("login"){
            LoginScreen(topPadding,navController)
        }
        composable("signup"){
            SignUpScreen(topPadding,navController)
        }

    }
}