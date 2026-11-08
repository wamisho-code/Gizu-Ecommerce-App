package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.michroma_regular_font
import com.example.ecomerceapp.viewmodel.CartViewModel

@Composable
fun CartScreen(innerPadding: PaddingValues,
               onProductClicked:(ProductModel)-> Unit,
               viewModel: CartViewModel= viewModel()){
    val cartItems  by viewModel.cartItems.observeAsState( emptyList())
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding()+10.dp,
                start = 10.dp, end = 10.dp,
                bottom = innerPadding.calculateBottomPadding()+30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My Cart",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${cartItems.size} items",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (cartItems.isEmpty()){
            Text(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.Gray.copy(alpha = 0.45f))
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                text = "You have nothing in your cart!"
            )
        }
        else{
            CartListUi(carts = cartItems,
                onProductClicked= onProductClicked,
                viewModel=viewModel
            )
        }


    }


}