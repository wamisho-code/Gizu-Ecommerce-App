package com.example.ecomerceapp.model.home_screen_models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemModel(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeNumber:Int,
    val route:String

)