package com.example.userdirectory.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.userdirectory.model.ApiState
import com.example.userdirectory.model.FetchedUserDataClass
import com.example.userdirectory.reusablecomposable.CustomToolbar
import com.example.userdirectory.ui.theme.LocalTypography
import com.example.userdirectory.ui.theme.UserDirectoryTheme
import com.example.userdirectory.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUserList(viewModel: UserViewModel) {
    val apiStateFromViewModel by viewModel.filteredUsers.collectAsState()
    var textFieldText by rememberSaveable {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.fetchUsersFromRepo()
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    BackHandler(enabled = isFocused) {
        focusManager.clearFocus()
        isFocused = false
    }
    UserDirectoryTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                CustomToolbar("User Listings")
                OutlinedTextField(
                    value = textFieldText,
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraLarge,
                    onValueChange = {
                        textFieldText = it
                        Log.d("SearchQuery", "HomeScreenUserList: $it")
                        viewModel.searchQuery.value = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search, contentDescription = ""
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    ),
                    trailingIcon = {
                        if (textFieldText.isNotEmpty()) {
                            IconButton(onClick = {
                                textFieldText = ""
                                viewModel.searchQuery.value = textFieldText
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Clear,
                                    contentDescription = "Clear Text"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .onFocusChanged {
                            isFocused = true
                        },
                    label = {
                        Text(
                            "Search For Users", style = MaterialTheme.typography.bodySmall
                        )
                    },
                    textStyle = MaterialTheme.typography.bodySmall
                )
                when (apiStateFromViewModel) {
                    is ApiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onBackground,
                                strokeWidth = 8.dp,
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    }

                    is ApiState.Error -> {
                        Text("Failed To Fetch Data with Error Message {${(apiStateFromViewModel as ApiState.Error).message}}")
                    }

                    is ApiState.Success -> {
                        val userList =
                            (apiStateFromViewModel as ApiState.Success<List<FetchedUserDataClass>>).data
                        if (userList.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Users Found!",
                                    style = LocalTypography.current.customContentNotAvailableText
                                )
                            }
                        } else {
                            LazyColumn(
                                content = {
                                    items(userList) { user ->
                                        SampleUser(user)
                                    }
                                },
                                modifier = Modifier.padding(top = 10.dp, end = 10.dp, start = 10.dp)
                            )
                        }

                    }
                }

            }
        }

    }
}



