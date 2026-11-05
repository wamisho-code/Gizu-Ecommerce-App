package com.example.ecomerceapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.screen.main_middle_screens.CartScreen
import com.example.ecomerceapp.screen.main_middle_screens.ListingsScreen
import com.example.ecomerceapp.screen.main_middle_screens.PostProductScreen
import com.example.ecomerceapp.screen.main_middle_screens.SettingsScreen
import com.example.ecomerceapp.screen.main_middle_screens.UpdatesScreen
import com.example.ecomerceapp.screen.home_screen_ui_components.BottomNavBarUI
import com.example.ecomerceapp.screen.home_screen_ui_components.TopAppBarUI
import com.example.ecomerceapp.viewmodel.HomeUIViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeUIViewModel: HomeUIViewModel= viewModel(),navController: NavController){
    val navControllerMiddleContent = rememberNavController()
    var selectedProduct by remember {
        mutableStateOf<ProductModel?>(null)
    }
    LaunchedEffect(Unit) {
        homeUIViewModel.getUserInfo { bool, message ->
            if (!bool) {
                println("me: $message")
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        topBar = {
            TopAppBarUI(homeUIViewModel,navController=navController)
        },
        bottomBar = {
            BottomNavBarUI(navControllerMiddleContent)
        },
    ) { innerPadding->
        Spacer(modifier = Modifier.height(10.dp))
        NavHost(
            navController = navControllerMiddleContent,
            startDestination = "listing",

        ) {

            composable("listing"){
                ListingsScreen(
                    padValues = innerPadding,
                    onProductClicked= { product->
                        selectedProduct=product
                        navControllerMiddleContent.navigate("product-detail")
                    }

                )
            }
            composable("post-product"){
                PostProductScreen(innerPadding = innerPadding)
            }
            composable("updates"){
                UpdatesScreen()
            }
            composable("settings"){
                SettingsScreen()
            }
            composable("cart"){
                CartScreen()
            }
            composable("product-detail"){ backStackEntry->
                selectedProduct?.let {
                    ProductDetailScreen(
                        paddingValues = innerPadding,
                        product = selectedProduct
                    )
                }
            }
        }

    }
}
