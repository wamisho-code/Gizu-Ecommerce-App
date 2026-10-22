package com.example.ecomerceapp.screen

import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun VerifyEmailScreen(navController: NavController,authenticationViewModel: AuthenticationViewModel){
    val context = LocalContext.current
    var callAgain=true
    val isEmailVerified by authenticationViewModel.isEmailVerified.collectAsStateWithLifecycle()

    // 1. Automatically navigate to home as soon as isEmailVerified becomes true
    LaunchedEffect(isEmailVerified) {
        if (isEmailVerified) {
            // Use popUpTo to clear the login/verify stack so the back button doesn't return here
            navController.navigate("home") {
                popUpTo("verifyEmail") { inclusive = true }
            }
        }
    }

    // 2. Poll Firebase every 3 seconds to check if the user verified their email
    LaunchedEffect(Unit) {
        while (isActive && callAgain) {
            authenticationViewModel.checkEmailIsVerified { success, message ->
                // Optionally handle errors, but hide silent background failures
                if (!success && message != null && !message.contains("network", ignoreCase = true)) {
                    // Fail silently in the background unless it's a critical error
                    if(message=="User is not logged in")
                        callAgain=false
                    ToastUtility.makeShortToast(context,"Something went Wrong")

                }
            }
            if(callAgain)
                delay(3000) // Wait 3 seconds before checking again
        }
    }

    // 3. Force an immediate check right when the user returns to the app (Optional but fast)
    LifecycleEventEffect (Lifecycle.Event.ON_RESUME) {
        authenticationViewModel.checkEmailIsVerified { _, _ -> }
    }



    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 60.dp, start = 20.dp, end = 20.dp)

    ) {
        IconButton(
            onClick = {
                navController.navigate("signup"){
                    popUpTo("auth"){inclusive=false}
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = "back"
            )

        }
        Spacer(modifier = Modifier.height(200.dp))
        Text(
            text = "We have sent verification link to \nemail address you entered",
            fontFamily = montserrat_regular_font,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(400.dp))
        Button(
            onClick = {
                authenticationViewModel.verifyEmail(){success,message->
                    val message= when{
                        success-> "Verification link has been resent to \nemail address you entered"
                        else -> message
                    }
                    ToastUtility.makeLongToast(context,message!!)
                    //println(message)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )

        ) {
            Text(
                text = "Resend Link",
                fontFamily = montserrat_regular_font,
            )
        }

    }


}