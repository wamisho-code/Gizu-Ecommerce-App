package com.example.ecomerceapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.viewmodel.AuthenticationViewModel

@Composable
fun VerifyEmailScreen(topPadding: Dp,navController: NavController,authenticationViewModel: AuthenticationViewModel= viewModel()){
    val context = LocalContext.current
    val isEmailVerified by authenticationViewModel.isEmailVerified.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        authenticationViewModel.checkEmailIsVerified(){sucess,message->
                if(!sucess && message!=null){
                    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                }
        }
    }
    // Navigate automatically when verification is detected
    LaunchedEffect(isEmailVerified) {
        if (isEmailVerified) {
            navController.navigate("home")

            }
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = topPadding, start = 20.dp, end = 20.dp)

    ) {
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
                        success-> "Account Created Successfully!"
                        else -> message
                    }
                    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()

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