package com.example.ecomerceapp.screen.home_screen_ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.pattaya_regular_font
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.viewmodel.HomeUIViewModel

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
            IconButton(
                onClick = {
                    navController.navigate("search-screen")
                }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Filled.Search,
                    tint = Orange,
                    contentDescription = null

                )
            }
            IconButton(
                onClick = {
//                    homeUIViewModel.logout()
//                    navController.navigate("auth")
                }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Filled.Person,
                    tint = Orange,
                    contentDescription = null

                )
            }

        }

    )
}