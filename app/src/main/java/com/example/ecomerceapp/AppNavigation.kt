package com.example.ecomerceapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.screen.authentication_screens.AuthScreen
import com.example.ecomerceapp.screen.HomeScreen
import com.example.ecomerceapp.screen.authentication_screens.LoginScreen
import com.example.ecomerceapp.screen.search_screen.SearchScreen
import com.example.ecomerceapp.screen.authentication_screens.SignUpScreen
import com.example.ecomerceapp.screen.authentication_screens.VerifyEmailScreen
import com.example.ecomerceapp.viewmodel.authentication_viewmodels.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun  AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthenticationViewModel = viewModel()
    val startDestination = remember {
        val user = FirebaseAuth.getInstance().currentUser

        when {
            user == null -> "auth"
            !user.isEmailVerified -> "verify-email"
            else -> "home"
        }
    }


    NavHost(navController, startDestination = startDestination) {
        composable("search-screen"){
            SearchScreen()
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
