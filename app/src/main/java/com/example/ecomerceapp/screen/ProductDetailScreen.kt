package com.example.ecomerceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ProductDetailScreen(paddingValues: PaddingValues,product: ProductModel?){
    var relatedToggle by remember {
        mutableStateOf("Show related products")
    }
    var showRelated by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()
    LaunchedEffect(product?.productId) {
        scrollState.scrollTo(0)
    }
    LaunchedEffect(showRelated) {
        if (showRelated) {
            snapshotFlow { scrollState.maxValue }
                .distinctUntilChanged()
                .collect { maxScroll ->
                    scrollState.animateScrollTo(maxScroll)
                }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        if (product !=null){
            ProductDetailImageSlide(product.productImages )
            Spacer(modifier = Modifier.height(10.dp))
            ProductDetailTextualUi(product = product)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                        .padding(end = 5.dp),
                    thickness = 1.dp,
                    color = Orange
                )
                Row(
                    modifier = Modifier.wrapContentWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(brush = orangeGradientVertical)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clickable(
                            onClick = {
                                if(!showRelated){
                                    relatedToggle = "Hide related products"
                                }else{
                                    relatedToggle= "Show related products"
                                }
                                showRelated=!showRelated
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = Color.White,
                        maxLines = 1,
                        softWrap = false,
                        text = relatedToggle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = Color.White,
                        imageVector = if(!showRelated)Icons.Default.ArrowDropDown
                        else Icons.Default.KeyboardArrowUp,
                        contentDescription = null
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                        .padding(start = 5.dp),
                    thickness = 1.dp,
                    color = Orange
                )

            }

            if(showRelated){
                Spacer(modifier = Modifier.height(10.dp))
                RelatedProductsUi(product=product)

            }
            Spacer(modifier = Modifier.height(60.dp))


        }

    }
}