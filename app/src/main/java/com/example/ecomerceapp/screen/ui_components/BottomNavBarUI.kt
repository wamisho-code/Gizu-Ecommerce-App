package com.example.ecomerceapp.screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.utility.BottomNavItems

@Composable
fun BottomNavBarUI(navController: NavController){
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Box() {
        NavigationBar(
            //modifier = Modifier.height(64.dp),
            modifier = Modifier.align(Alignment.BottomCenter),
            containerColor = whiteBackgroundColor,
            // 2. Remove default window insets padding if it's pushing the bar up
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            BottomNavItems.bottomNavItems.forEachIndexed { index, item ->
                if(item==null){
                    Spacer(modifier = Modifier.weight(1f))
                }else{

                val selected = currentRoute == item!!.route   // CHANGED

                NavigationBarItem(
                        modifier = Modifier.weight(1f),
                        label = {
                            Text(item.title)
                        },
                        selected = selected,
                        onClick = {

                            navController.navigateToTab(item.route)
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
                                    imageVector = if(selected){
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
        IconButton(
            onClick = {
                navController.navigateToTab("post-product")
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-20).dp)
                .size(56.dp)
                .background(
                    brush = orangeGradientVertical,
                    shape = CircleShape
                ),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Post product"
                )
            }
        }
    }
}
fun NavController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}