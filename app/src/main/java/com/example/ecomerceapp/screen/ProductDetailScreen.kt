package com.example.ecomerceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.SuccessGreen

@Composable
fun ProductDetailScreen(paddingValues: PaddingValues,product: ProductModel?){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding())
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(SuccessGreen)
    ) {


    }
}