package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.ecomerceapp.ui.theme.michroma_regular_font

@Composable
fun NotificationScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notifications here!",
            style = TextStyle(
                fontFamily = michroma_regular_font,
                fontWeight = FontWeight.Bold
            )
        )
    }

}