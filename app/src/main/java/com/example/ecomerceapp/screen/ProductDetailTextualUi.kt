package com.example.ecomerceapp.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomerceapp.R
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Crimson
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.ui.theme.SuccessGreen
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.HomeUIViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductDetailTextualUi(homeUIViewModel: HomeUIViewModel= viewModel(),product: ProductModel){
    var howManyProduct by remember {
        mutableStateOf(1)
    }
    val context= LocalContext.current
    val userInfo by homeUIViewModel.userDto.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        homeUIViewModel.getUserInfo { bool, message ->
            if (!bool) {
                println("me: $message")
            }
        }
    }
    val price= NumberFormat.getNumberInstance(Locale.US).format(product.price)
    val quantity= NumberFormat.getNumberInstance(Locale.US).format(product.quantity)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(3f).padding(start = 10.dp, end = 10.dp),
            text = product.productName,
            color =Color.Black,
            maxLines = 2,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            modifier = Modifier.wrapContentSize()
                .clip(RoundedCornerShape(10.dp))
                .background(Blue)
                .padding(8.dp)
            ,
            text = product.condition.toString(),
            color =Color.White,
            maxLines = 2,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        IconButton(
            modifier = Modifier.wrapContentSize()
                .padding(10.dp),
            onClick = {},
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Crimson.copy(alpha = 0.35f)
            )
        ) {
            Icon(
                modifier = Modifier.size(19.dp),
                painter = painterResource(R.drawable.add__to_shopping_cart_icon),
                contentDescription = null,
                tint = Color.Black
            )
        }

    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        text = product.productDescription,
        color =Color.Gray,
        maxLines = 5,
        style = TextStyle(
            fontSize = 16.sp,
        )
    )
    Spacer(modifier = Modifier.height(15.dp))
    product.attributes.forEach { (key, value) ->
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(2.5f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                    .background(Blue.copy(alpha = 0.25f))
                    .padding(15.dp)
                ,
                text = key ,
                textAlign = TextAlign.Center,
                color =Color.Black,
                maxLines = 3,
                style = TextStyle(
                    fontSize = 16.sp,
                )
            )

            Text(
                modifier = Modifier.weight(2.5f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                    .background(Orange.copy(alpha = 0.25f))
                    .padding(15.dp)
                ,
                text = value ,
                textAlign = TextAlign.Center,
                color =Color.Black,
                maxLines = 3,
                style = TextStyle(
                    fontSize = 16.sp,
                )
            )

        }
        Spacer(modifier = Modifier.height(7.dp))

    }
    Spacer(modifier = Modifier.height(15.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
            ,
            text = "Quantity:" ,
            color =Color.Black,
            maxLines = 2,
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
            ,
            text = quantity ,
            color =Color.Black,
            maxLines = 2,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )

    }
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(10.dp))
            .background(SuccessGreen.copy(alpha = 0.15f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .background(SuccessGreen)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Price",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(SuccessGreen.copy(alpha = 0.15f))
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$price ETB",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))

    // Buyer requested quantity start

    if(product.quantity>1){
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "How many do you want:",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.Gray.copy(alpha = 0.6f))
                    .padding(vertical = 7.dp, horizontal = 10.dp),
                text =howManyProduct.toString(),
                color = Color.Black,
                softWrap = false,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.width(7.dp))
            Column() {
                Icon(
                    modifier = Modifier.clickable(
                        onClick = {
                            if(howManyProduct>product.quantity-1) {
                                howManyProduct = product.quantity
                                ToastUtility.makeShortToast(context,
                                    message = "The available quantity is ${product.quantity}")
                            }else
                                howManyProduct += 1
                        }
                    )
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "+"
                )
                Icon(
                    modifier = Modifier.clickable(
                        onClick = {
                            if(howManyProduct<=1)
                                howManyProduct= 1
                            else
                                howManyProduct -= 1
                        }
                    )
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "+"
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))

    }
    // Buyer requested quantity end

    // Seller info section start
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Filled.Person,
            contentDescription = "Seller: ",
            tint = Color.Gray
        )
        SelectionContainer {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = userInfo.fullName?:"",
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Filled.Phone,
            contentDescription = "Phone: ",
            tint = Color.Gray
        )
        SelectionContainer {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = userInfo.phoneNumber?:"",
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }

    // Seller info section end

    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Filled.Email,
            contentDescription = "email: ",
            tint = Color.Gray
        )
        SelectionContainer {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = userInfo.email?:"",
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(30.dp))

    // Buy now button

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .height(52.dp)
                .fillMaxWidth(0.75f),
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SuccessGreen,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(21.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Buy Now",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    Spacer(modifier = Modifier.height(50.dp))





}