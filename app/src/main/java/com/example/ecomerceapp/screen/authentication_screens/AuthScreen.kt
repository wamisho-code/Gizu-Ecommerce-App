package com.example.ecomerceapp.screen.authentication_screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.montserrat_bold_font
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.ui.theme.orangeGradientHorizontal
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor

@Composable
fun AuthScreen(navController: NavController) {
    val context = LocalContext.current
    BackHandler {
        (context as Activity).finish()
    }
    // 1. Load the Lottie JSON file from the raw resources directory
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.ecommerce_animation))

    // 2. Control the animation state (autoplay and loop infinitely)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .background(whiteBackgroundColor)
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = composition,
            progress = { progress },
        )
        Spacer(modifier= Modifier.height(10.dp))
        Text(
            text = "Start your Shopping journey now",
            style =  TextStyle(
                fontFamily = montserrat_bold_font,
                fontSize = 23.sp
            ),
            textAlign = TextAlign.Center

        )
        Spacer(modifier= Modifier.height(10.dp))
        Text(
            text = "Best ecom app with best price",
            style =  TextStyle(
                fontFamily = montserrat_regular_font,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center

        )
        Spacer(modifier= Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("login")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .width(120.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(brush = orangeGradientHorizontal)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier= Modifier.height(15.dp))
        OutlinedButton (
            modifier = Modifier.width(120.dp),
            onClick = {
                navController.navigate("signup")
            },
            border = BorderStroke(width = 2.dp, brush = orangeGradientHorizontal)
        ) {
            Text(

                text = "Sign-up",
                color = Color.Transparent,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    brush = orangeGradientHorizontal
                )
            )
        }

    }
}