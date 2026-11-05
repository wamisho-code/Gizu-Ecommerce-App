package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.screen.home_screen_ui_components.VectorImgCardSlide
import com.example.ecomerceapp.screen.main_middle_screens.listing_ui_components.ListingLoadingUi
import com.example.ecomerceapp.screen.main_middle_screens.listing_ui_components.ProductsListUi
import com.example.ecomerceapp.viewmodel.ProductListingViewModel

@Composable
fun ListingsScreen(padValues: PaddingValues,
                   viewModel: ProductListingViewModel = viewModel(),
                   onProductClicked:(ProductModel)-> Unit){
    val uiSate by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .padding(top = padValues.calculateTopPadding(), bottom = padValues.calculateBottomPadding())
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp,
                bottomEnd = 0.dp, bottomStart =0.dp))
            .background(Color.Gray.copy(0.07f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        VectorImgCardSlide()
        Spacer(modifier = Modifier.height(20.dp))

        when{
            uiSate.isLoading-> ListingLoadingUi(null)
            uiSate.errorMessage != null -> ListingLoadingUi(uiSate.errorMessage)
            else -> ProductsListUi(
                paddingValues = padValues,
                products = uiSate.products,
                onProductClicked= onProductClicked
                )
        }

    }


}