package com.example.ecomerceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.pattaya_regular_font
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.utility.ToggleSystemBars
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    ToggleSystemBars(false)

    Row (
        modifier = Modifier.fillMaxSize()
            .background(brush = orangeGradientVertical),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            color = Color.White,
            text = "Gizu",
            style = TextStyle(
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = pattaya_regular_font,

            )
        )

    }
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate("first_screen"){
            popUpTo("splash-screen"){inclusive=true}
        }
    }
}