package com.example.ecomerceapp.utility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import com.example.ecomerceapp.model.BottomNavItemModel

class BottomNavItems {
    companion object{
        val bottomNavItems= listOf<BottomNavItemModel>(
            BottomNavItemModel(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                badgeNumber = 0,
                route = "listing"
            ),
            BottomNavItemModel(
                title = "Cart",
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart,
                badgeNumber = 13,
                route = "cart"
            ),
//
//            BottomNavItemModel(
//                title = "Post",
//                selectedIcon = Icons.Filled.AddCircle,
//                unselectedIcon = Icons.Outlined.AddCircle,
//                badgeNumber = 0,
//                route = "post-product"
//            ),
            BottomNavItemModel(
                title = "Notification",
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
                badgeNumber = 89,
                route = "notification"
            ),
            BottomNavItemModel(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                badgeNumber = 0,
                route = "settings"
            )

            )
    }
}