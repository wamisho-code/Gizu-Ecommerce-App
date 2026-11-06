package com.example.ecomerceapp.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ecomerceapp.model.product_models.ProductImageModel
import com.example.ecomerceapp.ui.theme.Orange

@Composable
fun ProductDetailImageSlide(productImages: List<ProductImageModel>) {

    val pagerState = rememberPagerState {
        productImages.size
    }
    HorizontalPager(
        modifier = Modifier.fillMaxWidth()
            .height(350.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        state = pagerState,
        pageSpacing = 16.dp,
    ) { page->
        AsyncImage(
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            model = productImages[page].url,
            contentDescription = "Product image",
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(productImages.size){ index->
            val isSelected= pagerState.currentPage == index
            val color = if(isSelected) Orange else Color.Gray
            val lineWidth by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 12.dp,
                label = "lineWidth"
            )
            val lineHeight by animateDpAsState(
                targetValue = if(isSelected) 6.dp else 4.dp,
                label = "lineHeight"
            )
            Box(
                modifier = Modifier.
                size(width = lineWidth, height = lineHeight)
                    .clip(CircleShape)
                    .background(color),
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }

}