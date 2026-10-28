package com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.SuccessGreen
import com.example.ecomerceapp.viewmodel.PostProductViewModel

@Composable
fun PostingProductStatusUi(viewModel: PostProductViewModel){
    val compositionForLoading by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val compositionForSuccess by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.success_animation)
    )
    // 2. Control the animation state (autoplay and loop infinitely)
    val progressForEver by animateLottieCompositionAsState(
        composition = compositionForLoading,
        iterations = LottieConstants.IterateForever
    )
    val progressOnce by animateLottieCompositionAsState(
        composition = compositionForSuccess,
        iterations = 1,
        speed = 0.5f
    )
    val isPosting by viewModel.isPosting.collectAsStateWithLifecycle()
    val isPostingSuccess by viewModel.postSuccess.collectAsStateWithLifecycle()
    val postingStatusMessage by viewModel.postMessage.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isPosting){
            LottieAnimation(
                modifier = Modifier.width(250.dp),
                composition=compositionForLoading,
                contentScale = ContentScale.Crop,
                progress = progressForEver)
        }
        else{
            if(isPostingSuccess){
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(100.dp),
                        composition = compositionForSuccess,
                        progress = { progressOnce }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = postingStatusMessage!!,
                        color = SuccessGreen,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }

            }else if( postingStatusMessage!=null){
                Row(
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.error.copy(alpha = 0.35f))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(R.drawable.error_icon),
                        tint = Color.White,
                        contentDescription = "Error: "
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = postingStatusMessage!!,
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

        }
    }
}