package com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.viewmodel.PostProductViewModel
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun AddAttributeUi(uiState: PostProductUiState,viewModel: PostProductViewModel){
    Text(
        text = "Attributes",
        fontSize =18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = "You can select a maximum of 5 attributes like color : red",
        fontSize =15.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray
    )
    Spacer(modifier = Modifier.height(10.dp))
    //attributes
    Column(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
    ) {
        uiState.attributes.forEach { (key, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = key,
                    onValueChange = {
                        viewModel.updateAttributeOldKey(
                            oldKey = key,
                            newKey = it,
                            value=value
                        )
                    },
                    label = null,
                    singleLine = true,
                    placeholder = {
                        Text(
                            "",
                            fontStyle = FontStyle.Italic
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Orange,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = value,
                    onValueChange = {
                        viewModel.updateAttributeOldValue(
                            newValue = it,
                            key=key
                        )
                    },
                    label = null,
                    singleLine = true,
                    placeholder = {
                        Text(
                            "",
                            fontStyle = FontStyle.Italic
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Orange,
                    ),
                )
                IconButton(
                    onClick = {
                        viewModel.removeAttribute(key)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        if(uiState.attributes.size<5){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.attributeKey,
                    onValueChange = {
                        viewModel.setAttributeKey(it)
                    },
                    label = null,
                    singleLine = true,
                    placeholder = {
                        Text(
                            "Color",
                            fontStyle = FontStyle.Italic
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Orange,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.attributeValue,
                    onValueChange = {
                        viewModel.setAttributeValue(it)
                    },
                    label = null,
                    singleLine = true,
                    placeholder = {
                        Text(
                            "Red",
                            fontStyle = FontStyle.Italic
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Orange,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.addAttribute()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                text = "Add attribute",
                color = Color.White
            )
        }
    }
}