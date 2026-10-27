package com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomerceapp.model.input_validation_models.PostProductFirstPartValidationResult
import com.example.ecomerceapp.model.product_models.enums.ProductCategory
import com.example.ecomerceapp.ui.theme.Orange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableCategoryDropdown(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory) -> Unit,
    errorStatus: PostProductFirstPartValidationResult
) {
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember {
        mutableStateOf(selectedCategory?.displayName ?: "")
    }

    val filteredCategories = remember(searchText) {
        ProductCategory.entries.filter {
            it.displayName.contains(searchText, ignoreCase = true)
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                expanded = true
            },
            isError = !errorStatus.isCategorySelected,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            label = { Text(
                text = "Product Category",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ) },
            placeholder = { Text("Search category...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange,
                focusedLabelColor = Color.Black
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.height(250.dp),
            onDismissRequest = { expanded = false }
        ) {
            filteredCategories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.displayName) },
                    onClick = {
                        searchText = category.displayName
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }

            if (filteredCategories.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("No categories found") },
                    onClick = {},
                    enabled = false
                )
            }
        }
    }
}
