package com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ecomerceapp.R
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.viewmodel.PostProductViewModel

@Composable
fun GetImagesFromPhoneUi(uiState: PostProductUiState,viewModel: PostProductViewModel){
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris->
        viewModel.setImages(uris)
    }
    //adding images
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Images",
        fontSize =18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = "You can select a maximum of 5 images",
        fontSize =15.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray
    )
    Spacer(modifier = Modifier.height(15.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items=uiState.selectedImages,
            key={it.toString()}
        ){
                uri->
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                AsyncImage(
                    model = uri,
                    contentDescription = "Select product image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
                IconButton(
                    onClick = {
                        viewModel.removeImage(uri)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,

                        )
                }
            }
        }
        if (uiState.selectedImages.size < 5) {

            item {

                OutlinedButton (
                    border = BorderStroke(2.dp,Blue),
                    onClick = {
                        imagePicker.launch("image/*")
                    },
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(R.drawable.add_image_icon),
                            tint = Blue,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Add",
                            color = Blue
                        )
                    }
                }
            }
        }
    }
}