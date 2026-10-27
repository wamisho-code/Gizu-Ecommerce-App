package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecomerceapp.screen.home_screen_ui_components.VectorImgCardSlide
import com.example.ecomerceapp.ui.theme.michroma_regular_font

@Composable
fun ListingsScreen(topPad: Dp){
    Column(
        modifier = Modifier
            .padding(top = topPad)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp,
                bottomEnd = 0.dp, bottomStart =0.dp))
            .background(Color.Gray.copy(0.07f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        VectorImgCardSlide()

        Text(
            text = "Listings",
            style = TextStyle(
                fontFamily = michroma_regular_font,
                fontWeight = FontWeight.Bold
            )
        )
    }


}