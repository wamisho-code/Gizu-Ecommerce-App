package com.example.ecomerceapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemModel(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeNumber:Int,
    val route:String

)
