package com.example.ecomerceapp.screen.post_product_ui_parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomerceapp.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun PostProductPagerButtons(pagerSate: PagerState, pageCount: Int) {
    val scope = rememberCoroutineScope ()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if(pagerSate.currentPage in 1..<2){
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),

                onClick = {
                    scope.launch {
                        pagerSate.animateScrollToPage(
                            pagerSate.currentPage-1
                        )
                    }
                }
            ) {
                Text(
                    text = "Back",
                    color = Color.White
                )
            }
        }else{Spacer(modifier = Modifier.width(1.dp))}
        if(pagerSate.currentPage < pageCount-1){
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                onClick = {
                    scope.launch {
                        pagerSate.animateScrollToPage(
                            pagerSate.currentPage+1
                        )
                    }
                }
            ) {
                Text(
                    text = "Next",
                    color = Color.White
                )
            }
        }else{
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                contentPadding = PaddingValues(
                    horizontal = 24.dp,
                    vertical = 12.dp
                ),
                onClick = {

                }
            ) {
                Text(
                    text = "Post",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}