package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecomerceapp.local_database.entity.CartEntity
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.repository.CartRepository
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.viewmodel.CartViewModel

@Composable
fun CartListUi(carts: List<CartEntity>,
               onProductClicked:(ProductModel)-> Unit,
               viewModel: CartViewModel){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = carts,
            key = {it.id}
        ){ cart->
            CartCardUi(
                cart=cart,
                onProductClicked=onProductClicked,
                viewModel=viewModel
            )
        }
    }

}