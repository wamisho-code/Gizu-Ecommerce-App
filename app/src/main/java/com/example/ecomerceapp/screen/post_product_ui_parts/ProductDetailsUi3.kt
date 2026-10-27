package com.example.ecomerceapp.screen.post_product_ui_parts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.viewmodel.PostProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsUi3(uiState: PostProductUiState,viewModel: PostProductViewModel){
    val context= LocalContext.current
    val errorStatus by viewModel.inputStatusForThirdPart.collectAsStateWithLifecycle()
    val conditionList =listOf<String>("New","Old")
    var expanded by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_animation))

    // 2. Control the animation state (autoplay and loop infinitely)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Text(
        text = "Price",
        fontSize =18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = uiState.price,
        isError = !errorStatus.isPriceValid,
        onValueChange = {
                viewModel.setPrice(it,context)
        },
        label = null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        placeholder = {
            Text(
                "200",
                fontStyle = FontStyle.Italic
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Orange,
        ),
    )
    Spacer(modifier = Modifier.height(15.dp))

    Text(
        text = "Quantity in stock",
        fontSize =18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        label = null,
        modifier = Modifier.fillMaxWidth(),
        isError = !errorStatus.isQuantityValid,
        value = uiState.quantity,
        onValueChange = {
            viewModel.setQuantity(it,context)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Orange,
        )
    )

    Spacer(modifier = Modifier.height(20.dp))
    ExposedDropdownMenuBox(
        expanded=expanded,
        onExpandedChange = {
            expanded=it
        }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            label = {
                Text(
                    text = "Condition",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )},
            onValueChange = {},
            value = uiState.condition,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)

            },
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange,
                focusedLabelColor = Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded=expanded,
            onDismissRequest = {expanded=false}
        ) {
            conditionList.forEach { condition->
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                    .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.45f),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    text = {Text(condition)},
                    onClick = {
                        viewModel.setCondition(condition)
                        expanded=false
                    }
                )
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(60.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading){
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition=composition,
                    contentScale = ContentScale.Crop,
                    progress = progress
                )
            }
    }
}