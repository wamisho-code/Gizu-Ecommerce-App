package com.example.ecomerceapp.screen.main_middle_screens.listing_ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecomerceapp.R
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.screen.ProductDetailScreen
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.SuccessGreen

@Composable
fun ProductCardUi(paddingValues: PaddingValues,product: ProductModel,
                  onProductClicked:(ProductModel)-> Unit){
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(260.dp)
            .clickable(
                onClick = {
                    onProductClicked(product)
//                    navController.currentBackStackEntry
//                        ?.savedStateHandle
//                        ?.set("product",product)
//                    navController.navigate("product-detail")
                }
            ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Box() {

                AsyncImage(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    model = product.productImages.firstOrNull()?.url,
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {
                        // Add product to cart
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(40.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = CircleShape
                        ),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White.copy(alpha = 0.95f),
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add__to_shopping_cart_icon),
                        contentDescription = "Add to cart",
                        modifier = Modifier.size(21.dp),
                        tint = Color.Black
                    )
                }

            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                text = product.productName,
                maxLines = 2,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.weight(3f)
                    .padding(start = 10.dp, end = 10.dp),
                text = product.price.toString() +" ETB",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .background(
                        if (product.condition.toString()=="NEW") Blue
                        else Orange
                    )
                    .padding(start = 10.dp, end = 10.dp),
                text = product.condition.toString(),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                ),
                color = Color.White
            )


        }
    }
}