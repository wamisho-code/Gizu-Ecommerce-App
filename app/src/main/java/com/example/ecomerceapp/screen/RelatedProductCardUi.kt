package com.example.ecomerceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import coil3.compose.AsyncImage
import com.example.ecomerceapp.R
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Crimson
import com.example.ecomerceapp.ui.theme.Orange

@Composable
fun RelatedProductCardUi(product: ProductModel){
    Card(
        modifier = Modifier
            .height(240.dp)
            .width(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Product image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = product.productImages.firstOrNull()?.url,
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .size(32.dp),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White.copy(alpha = 0.9f)
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(17.dp),
                        painter = painterResource(
                            R.drawable.add__to_shopping_cart_icon
                        ),
                        contentDescription = "Add to cart",
                        tint = Color.Black
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Orange.copy(alpha = 0.6f))
                    .padding(horizontal = 9.dp, vertical = 7.dp)
            ) {

                // Product name
                Text(
                    text = product.productName,
                    maxLines = 2,
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                // Price
                Text(
                    text = "${product.price} ETB",
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.weight(0.2f))

                // Condition
                Text(
                    modifier = Modifier
                        .background(
                            color = if (product.condition.toString() == "NEW")
                                Blue else Orange,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(
                            horizontal = 7.dp,
                            vertical = 3.dp
                        ),
                    text = product.condition.toString(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}