package com.example.ecomerceapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.blueGradientHorizontal
import com.example.ecomerceapp.ui.theme.michroma_regular_font

@Composable
fun HomeScreen(topPadding: Dp,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top=topPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to Gizu Ecommerce!",
            color = Color.Transparent,
            style = TextStyle(
                fontFamily = michroma_regular_font,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                brush = blueGradientHorizontal
            )
        )
    }
}