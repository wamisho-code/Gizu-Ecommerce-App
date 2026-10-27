package com.example.ecomerceapp.screen.authentication_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.authentication_viewmodels.AuthenticationViewModel
import com.example.ecomerceapp.viewmodel.LoginUIViewModel

@Composable
fun LoginScreen(navController: NavHostController
                ,viewModel: LoginUIViewModel=viewModel(),
                authenticationViewModel: AuthenticationViewModel= viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    var showPassword by remember {
        mutableStateOf(false)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.login_boy_animation))

    // 2. Control the animation state (autoplay and loop infinitely)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .background(whiteBackgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            modifier = Modifier.size(220.dp),
            composition=composition,
            progress = progress
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Welcome back!",
            style =  TextStyle(
                fontFamily = montserrat_bold_font,
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Login in to your existing Account",
            style =  TextStyle(
                fontFamily = montserrat_bold_font,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center

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
        if(uiState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Orange
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val message= viewModel.validateInput(uiState)
                if (message==null){
                    viewModel.isLoadingTrue()
                    authenticationViewModel.login(loginUiState = uiState){bool,message->
                        viewModel.isLoadingFalse()
                        if(bool){
                            navController.navigate("home")
                        }else{
                            ToastUtility.makeLongToast(context,message ?: "Login failed. Please try again.")
                        }
                    }
                }else{
                    ToastUtility.makeLongToast(context,message)
                }


            },
            modifier = Modifier.width(160.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(brush = orangeGradientHorizontal),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Login",
                fontFamily = montserrat_bold_font,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(5.dp)
                .clickable(
                    onClick = {
                        navController.navigate("signup"){
                            popUpTo("login"){inclusive=true}
                        }
                    }
                ),
            text = "Create Account",
            color = Color.Blue,
            style = TextStyle(
                fontSize = 17.sp,
            ),
            textDecoration = TextDecoration.Underline
        )


    }

}

