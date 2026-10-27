package com.example.ecomerceapp.screen.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomerceapp.ui.theme.Blue
import com.example.ecomerceapp.ui.theme.orangeGradientVertical
import com.example.ecomerceapp.ui.theme.whiteBackgroundColor
import com.example.ecomerceapp.utility.ToastUtility
import com.example.ecomerceapp.viewmodel.SearchKeyViewModel

@Composable
fun SearchScreen(
    viewModel: SearchKeyViewModel = viewModel()
) {
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    val context = LocalContext.current

    var searchedText by rememberSaveable {
        mutableStateOf("")
    }

    var showSuggestions by rememberSaveable {
        mutableStateOf(true)
    }

    val suggestions by viewModel.searchSuggestions
        .observeAsState(emptyList())

    LaunchedEffect(searchedText) {
        viewModel.onSearchQueryChanged(searchedText)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Search header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(90.dp)
                .background(brush = orangeGradientVertical)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = whiteBackgroundColor,
                    unfocusedContainerColor = whiteBackgroundColor,
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                value = searchedText,
                onValueChange = {
                    searchedText = it
                    showSuggestions = true
                },
                placeholder = {
                    Text("Find anything you want to buy")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    if (searchedText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchedText = ""
                                showSuggestions = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val message =
                            if (searchedText.isBlank()) {
                                "Enter something to search"
                            } else {
                                "Search feature for $searchedText will come soon!"
                            }

                        ToastUtility.makeShortToast(context, message)
                    }
                ),
                singleLine = true
            )
        }

        // Suggestions directly below the search bar
        if (showSuggestions && searchedText.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                suggestions.forEach { suggestion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                searchedText = suggestion.word
                                showSuggestions = false
                            }
                            .padding(
                                horizontal = 12.dp,
                                vertical = 14.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(text = suggestion.word)
                    }
                }
            }
        }
    }
}
