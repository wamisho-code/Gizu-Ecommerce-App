package com.example.ecomerceapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.R
import com.example.ecomerceapp.screen.main_middle_screens.CartScreen
import com.example.ecomerceapp.screen.main_middle_screens.ListingsScreen
import com.example.ecomerceapp.screen.main_middle_screens.NotificationScreen
import com.example.ecomerceapp.screen.main_middle_screens.PostProductScreen
import com.example.ecomerceapp.screen.main_middle_screens.SettingsScreen
import com.example.ecomerceapp.screen.ui_components.BottomNavBarUI
import com.example.ecomerceapp.screen.ui_components.TopAppBarUI
import com.example.ecomerceapp.screen.ui_components.VectorImgCardSlide
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.pattaya_regular_font
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.utility.ToggleSystemBars
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
        }
    ) { innerPadding->
        Spacer(modifier = Modifier.height(10.dp))
        NavHost(navControllerMiddleContent, startDestination = "listing") {

            composable("listing"){
                ListingsScreen(innerPadding.calculateTopPadding())
            }
            composable("post-product"){
                PostProductScreen()
            }
            composable("notification"){
                NotificationScreen()
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
