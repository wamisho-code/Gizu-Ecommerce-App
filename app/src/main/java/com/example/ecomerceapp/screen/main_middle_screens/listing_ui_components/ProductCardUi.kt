package com.example.ecomerceapp.screen.main_middle_screens.listing_ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecomerceapp.R
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.screen.ProductDetailScreen
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Crimson
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.SuccessGreen
import com.example.ecomerceapp.utility.DataMappingUtility
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.CartViewModel

@Composable
fun ProductCardUi(paddingValues: PaddingValues,product: ProductModel,
                  onProductClicked:(ProductModel)-> Unit,
                  cartViewModel: CartViewModel= viewModel(),
                  ){
    val isAddedToCart by cartViewModel.isAddedToCart(product.productId)
        .observeAsState(false)
    val content = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(310.dp)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    model = product.productImages.firstOrNull()?.url,
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(145.dp)
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.productName,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${product.price} ETB",
                    softWrap = false,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    ),

                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                if (product.condition.toString() == "NEW") Blue
                                else Orange
                            )
                            .padding(vertical = 7.dp, horizontal = 12.dp),
                        text = product.condition.toString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Color.White
                    )

                    OutlinedButton(
                        modifier = Modifier.size(35.dp),
                        shape = RoundedCornerShape(7.dp),
                        border = BorderStroke(1.dp,Crimson),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if(isAddedToCart) Color.Transparent
                            else Crimson
                        ),
                        onClick = {
                            if (isAddedToCart){
                                cartViewModel.fetchCart(product.productId){cart->
                                    if(cart!==null)
                                        cartViewModel.deleteFromCart(cart.id)
                                    else
                                        ToastUtility.makeLongToast(
                                            content,
                                            "Something went wrong")
                                }

                            }else{
                                val cart = DataMappingUtility.mapCartEntity(product)
                                cartViewModel.addToCart(cart)
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                ,
                            painter = painterResource(
                                if(isAddedToCart) R.drawable.remove_cart_svg
                                else R.drawable.add__to_shopping_cart_icon
                            ),
                            contentDescription = "Add to cart",
                            tint = if (isAddedToCart) Crimson else Color.White
                        )
                    }

                }
            }

        }
    }
}