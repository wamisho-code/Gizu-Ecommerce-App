package com.example.ecomerceapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.RelatedProductsViewModel

@Composable
fun RelatedProductsUi(product: ProductModel,
                      viewModel: RelatedProductsViewModel= viewModel()){
    val uiState by viewModel.relatedProductsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(product.productId) {
        viewModel.getRelatedProducts(product=product)
    }
    if(uiState.isLoading)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = Orange
            )
        }
    else if(uiState.errorMessage == null)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                items = uiState.relatedProducts,
                key = {it.productId}
            ){ product->
                RelatedProductCardUi(product)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    else
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No related product found",
                color = Color.Gray,
                fontSize = 15.sp
            )
        }

}