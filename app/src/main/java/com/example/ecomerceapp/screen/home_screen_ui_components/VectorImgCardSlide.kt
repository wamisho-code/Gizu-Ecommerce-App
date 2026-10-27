package com.example.ecomerceapp.screen.home_screen_ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomerceapp.ui.theme.montserrat_regular_font
import com.example.ecomerceapp.utility.SlideCardUtility
import kotlinx.coroutines.delay

@Composable
fun VectorImgCardSlide() {


    val pagerState = rememberPagerState {
        SlideCardUtility.slideCardList.size
    }

    // Automatically slide every 4 seconds
    LaunchedEffect (Unit) {
        while (true) {
            delay(4000)

            val nextPage = (pagerState.currentPage + 1) % SlideCardUtility.slideCardList.size

            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager (
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->


        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(SlideCardUtility.slideCardList[page].image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = 1.15f
                            scaleY = 1.15f
                        },
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = SlideCardUtility.slideCardList[page].text,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Color.Black.copy(alpha = 0.35f)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = montserrat_regular_font,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}