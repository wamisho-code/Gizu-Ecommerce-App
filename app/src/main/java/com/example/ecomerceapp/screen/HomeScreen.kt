package com.example.ecomerceapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.blueGradientHorizontal
import com.example.ecomerceapp.ui.theme.michroma_regular_font
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.pattaya_regular_font
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.viewmodel.AuthenticationViewModel
import com.example.ecomerceapp.viewmodel.HomeUIViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeUIViewModel: HomeUIViewModel= viewModel(),navController: NavController){
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
            .statusBarsPadding()
        ,
        topBar = {
            TopAppBarUI(homeUIViewModel,navController=navController)
        }
    ) { innerPadding->

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(homeUIViewModel: HomeUIViewModel,navController: NavController){
    val userDto by homeUIViewModel.userDto.collectAsStateWithLifecycle()
    TopAppBar(
        title = {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    color = Color.Transparent,
                    text = "Gizu",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pattaya_regular_font,
                        brush = orangeGradientVertical
                    )
                )
                Text(
                    color = Color.Transparent,
                    text = "Shop with pennies",
                    style = TextStyle(
                        fontSize = 7.sp,
                        fontStyle = FontStyle.Italic,
                        fontFamily = montserrat_regular_font,
                        brush = orangeGradientVertical
                    )
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = whiteBackgroundColor
        ),
        actions = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        brush = orangeGradientVertical,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userDto.fullName?.firstOrNull()?.toString() ?: "",
                    color = Color.White
                )
            }
            IconButton(
                onClick = {
                    homeUIViewModel.logout()
                    navController.navigate("auth")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout_icon),
                    tint = Orange,
                    contentDescription = null

                )
            }

        }

    )
}