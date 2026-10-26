package com.example.ecomerceapp.screen.post_product_ui_parts.sub_ui_components

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.ecomerceapp.model.enums.ProductCategory
import com.example.ecomerceapp.ui.theme.Orange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableCategoryDropdown(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember {
        mutableStateOf(selectedCategory?.displayName ?: "")
    }

    val filteredCategories = ProductCategory.entries.filter {
        it.displayName.contains(searchText, ignoreCase = true)
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
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            label = { Text("Product category") },
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
