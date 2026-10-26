package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomerceapp.screen.post_product_ui_parts.PostProductPagerButtons
import com.example.ecomerceapp.screen.post_product_ui_parts.ProductDetailsUi1
import com.example.ecomerceapp.screen.post_product_ui_parts.ProductDetailsUi2
import com.example.ecomerceapp.screen.post_product_ui_parts.ProductDetailsUi3
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.montserrat_bold_font
import com.example.ecomerceapp.viewmodel.PostProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostProductScreen(viewModel: PostProductViewModel= viewModel(),innerPadding: PaddingValues){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pageCount=3
    val pagerSate = rememberPagerState(
        pageCount={pageCount}
    )
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .padding(top = innerPadding.calculateTopPadding()
                , start = 16.dp, end = 16.dp, bottom =innerPadding.calculateBottomPadding() ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Post product",
            style = TextStyle(
                fontFamily = montserrat_bold_font,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        LinearProgressIndicator(
            progress = {
                (pagerSate.currentPage +1)/ pageCount.toFloat()
            },
            modifier = Modifier.fillMaxWidth(),
            color = Blue,
            gapSize = 10.dp,
            trackColor = Color.Gray,
            drawStopIndicator = {}
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Step ${pagerSate.currentPage + 1} of $pageCount"
        )
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalPager(
            state = pagerSate,
            modifier = Modifier.weight(1f)
        ) { page->
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when(page){
                    0 -> ProductDetailsUi1(uiState,viewModel)
                    1 -> ProductDetailsUi2(uiState,viewModel)
                    2 -> ProductDetailsUi3(uiState,viewModel)

                }
                Spacer(modifier = Modifier.height(80.dp))
                PostProductPagerButtons(pagerSate,pageCount)
            }

        }


    }

}