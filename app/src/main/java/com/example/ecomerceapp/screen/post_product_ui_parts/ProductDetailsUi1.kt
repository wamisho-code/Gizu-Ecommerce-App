package com.example.ecomerceapp.screen.post_product_ui_parts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecomerceapp.model.product_models.enums.ProductCategory
import com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components.SearchableCategoryDropdown
import com.example.ecomerceapp.ui.theme.Orange
import com.example.ecomerceapp.uistate.PostProductUiState
import com.example.ecomerceapp.viewmodel.PostProductViewModel

@Composable
fun ProductDetailsUi1(uiState: PostProductUiState,
                      viewModel: PostProductViewModel, ){
    val errorStatus by viewModel.inputStatusForFirstPart.collectAsStateWithLifecycle()
    var selectedCategory by remember {
        mutableStateOf<ProductCategory?>(null)
    }
    // product name
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = uiState.name,
        onValueChange = {
            viewModel.setProductName(it)
        },
        isError = !errorStatus.isNameValid,
        label = {
            Text(
                text = "Product Name",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(
                "Samsung Galaxy A23 ",
                fontStyle = FontStyle.Italic
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Orange,
            focusedLabelColor = Color.Black
        ),
    )
    // product desc
    Spacer(modifier = Modifier.height(15.dp))
    OutlinedTextField(

        modifier = Modifier.fillMaxWidth()
            .height(140.dp),
        value = uiState.description,
        onValueChange = {
            viewModel.setDescription(it)
        },
        isError = !errorStatus.isDescriptionValid,
        label = {
            Text(
            text = "Product Description",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )},
        placeholder = {
            Text(
                "explain about the product...",
                fontStyle = FontStyle.Italic
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Orange,
            focusedLabelColor = Color.Black
        ),
    )
    Spacer(modifier = Modifier.height(20.dp))
    SearchableCategoryDropdown(
        selectedCategory = selectedCategory,
        onCategorySelected = { category ->
            selectedCategory = category
            viewModel.setCategory(category.name)
        },
        errorStatus = errorStatus
        )

}