package com.example.ecomerceapp.screen.main_middle_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecomerceapp.R
import com.example.ecomerceapp.local_database.entity.CartEntity
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.repository.CartRepository
import com.example.ecomerceapp.repository.ProductRepository
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.SuccessGreen
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.CartViewModel
@Composable
fun CartCardUi(
    cart: CartEntity,
    onProductClicked: (ProductModel) -> Unit,
    viewModel: CartViewModel
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val context =LocalContext.current
    var clickedButton by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 3.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                // Product information
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        model = cart.productImage,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(85.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = cart.productName,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "${cart.price} ETB",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = "Quantity: ${cart.quantity}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    // Delete button
                    Button(
                        modifier = Modifier
                            .defaultMinSize(
                                minWidth = 0.dp,
                                minHeight = 0.dp
                            )
                            .size(38.dp),
                        onClick = {
                            viewModel.deleteFromCart(cart.id)
                        },
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.delete_icon),
                            contentDescription = "Delete",
                            modifier = Modifier.size(21.dp),
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Orange.copy(alpha = 0.5f)
                )

                // Product details action
                TextButton (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    onClick = {
                        viewModel.getProduct(cart.productId){ product->
                            if(product==null)
                                ToastUtility.makeLongToast(context,"Something went wrong")
                            else{
                                clickedButton= cart.productId
                                onProductClicked(product)
                            }
                        }
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "See Product Details",
                        fontSize = 13.sp,
                        color = Blue
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        if (isLoading && clickedButton==cart.productId) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center),
                color = Orange
            )
        }
    }
}