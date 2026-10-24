package com.example.ecomerceapp.screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.utility.BottomNavItems

@Composable
fun BottomNavBarUI(navController: NavController){
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }
    NavigationBar(
        //modifier = Modifier.height(64.dp),
        containerColor = whiteBackgroundColor,
        // 2. Remove default window insets padding if it's pushing the bar up
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        BottomNavItems.bottomNavItems.forEachIndexed { index, item ->

                NavigationBarItem(

                    label = {
                        Text(item.title)
                    },
                    selected = index==selectedItemIndex,
                    onClick = {
                        selectedItemIndex=index
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeNumber!=0){
                                    Badge {
                                        Text(item.badgeNumber.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if(selectedItemIndex==index){
                                    item.selectedIcon
                                }else{item.unselectedIcon},
                                contentDescription = item.title
                            )

                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Orange,
                        selectedTextColor = Orange,
                        indicatorColor = Color.Transparent
                    )

                )

        }
    }
}