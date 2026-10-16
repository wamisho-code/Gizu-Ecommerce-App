package com.example.ecomerceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.Crimson
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.montserrat_bold_font
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.ui.theme.orangeGradientHorizontal
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(topPadding: Dp, navController: NavHostController,viewModel: SignUpViewModel= viewModel()) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val uiState by viewModel.signUpUiState.collectAsStateWithLifecycle();
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottie_signin_two_people_animation))

    // 2. Control the animation state (autoplay and loop infinitely)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .background(whiteBackgroundColor)
            .padding(top = topPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition=composition,
            progress = progress
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Create Account!",
            style =  TextStyle(
                fontFamily = montserrat_bold_font,
                fontSize = 22.sp
            ),
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.fullName,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Orange,
                focusedLabelColor = Crimson
            ),
            onValueChange = {
                viewModel.setFulName(it)
            },
            label = {
                Text("Full name", fontFamily = montserrat_regular_font)
            },
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.phoneNumber,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Orange,
                focusedLabelColor = Crimson
            ),
            onValueChange = {
                viewModel.setPhone(it)
            },
            placeholder = {
                Text("09-XX-XX-XX-XX", fontFamily = montserrat_regular_font)
            },
            label = {
                Text("Phone number", fontFamily = montserrat_regular_font)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.email,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Orange,
                focusedLabelColor = Crimson
            ),
            onValueChange = {
                viewModel.setEmail(it)
            },
            label = {
                Text("email address", fontFamily = montserrat_regular_font)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.password,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Orange,
                focusedLabelColor = Crimson
            ),
            onValueChange = {
                viewModel.setPassword(it)
            },
            label = {
                Text("password", fontFamily = montserrat_regular_font)
            },
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val showIcon=  if(showPassword==false)
                    painterResource(R.drawable.eye_show_icon)
                    else
                        painterResource(R.drawable.eye_hide_icon)
                IconButton(
                    onClick = {
                        showPassword= !showPassword
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = showIcon,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier.width(160.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(brush = orangeGradientHorizontal),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Create",
                fontFamily = montserrat_bold_font,
                fontSize = 18.sp
            )
        }




    }
}