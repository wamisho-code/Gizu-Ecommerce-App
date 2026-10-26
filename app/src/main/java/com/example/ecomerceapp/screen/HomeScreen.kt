package com.example.ecomerceapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.screen.main_middle_screens.CartScreen
import com.example.ecomerceapp.screen.main_middle_screens.ListingsScreen
import com.example.ecomerceapp.screen.main_middle_screens.PostProductScreen
import com.example.ecomerceapp.screen.main_middle_screens.SettingsScreen
import com.example.ecomerceapp.screen.main_middle_screens.UpdatesScreen
import com.example.ecomerceapp.screen.ui_components.BottomNavBarUI
import com.example.ecomerceapp.screen.ui_components.TopAppBarUI
import com.example.ecomerceapp.viewmodel.HomeUIViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeUIViewModel: HomeUIViewModel= viewModel(),navController: NavController){
    val navControllerMiddleContent = rememberNavController()
    LaunchedEffect(Unit) {
        homeUIViewModel.getUserInfo { bool, message ->
            if (!bool) {
                println("me: $message")
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        topBar = {
            TopAppBarUI(homeUIViewModel,navController=navController)
        },
        bottomBar = {
            BottomNavBarUI(navControllerMiddleContent)
        },
    ) { innerPadding->
        Spacer(modifier = Modifier.height(10.dp))
        NavHost(
            navController = navControllerMiddleContent,
            startDestination = "listing",

        ) {

            composable("listing"){
                ListingsScreen(innerPadding.calculateTopPadding())
            }
            composable("post-product"){
                PostProductScreen(innerPadding = innerPadding)
            }
            composable("updates"){
                UpdatesScreen()
            }
            composable("settings"){
                SettingsScreen()
            }
            composable("cart"){
                CartScreen()
            }
        }

    }
}
