package com.example.ecomerceapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.screen.AuthScreen
import com.example.ecomerceapp.screen.FirstScreen
import com.example.ecomerceapp.screen.HomeScreen
import com.example.ecomerceapp.screen.LoginScreen
import com.example.ecomerceapp.screen.SignUpScreen
import com.example.ecomerceapp.screen.SplashScreen
import com.example.ecomerceapp.screen.VerifyEmailScreen
import com.example.ecomerceapp.viewmodel.AuthenticationViewModel

@Composable
fun  AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthenticationViewModel = viewModel()

    NavHost(navController, startDestination = "splash-screen") {
        composable("splash-screen"){
            SplashScreen(navController)
        }
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("auth"){
            AuthScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("signup"){
            SignUpScreen(navController, authenticationViewModel = authViewModel)
        }
        composable("verify-email"){
            VerifyEmailScreen(navController, authenticationViewModel = authViewModel);
        }
        composable("home") {
            HomeScreen(navController=navController)
        }

    }
}