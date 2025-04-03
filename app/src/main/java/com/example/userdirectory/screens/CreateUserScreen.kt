package com.example.userdirectory.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController
import com.example.userdirectory.model.CreatedUserDataClass
import com.example.userdirectory.reusablecomposable.BaseScreen
import com.example.userdirectory.ui.theme.LocalTypography
import com.example.userdirectory.viewModel.UserViewModel


@Composable
fun CreateUserScreen(
    viewModel: UserViewModel,
    navController: NavController
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    var nameError = rememberSaveable { mutableStateOf<String?>(null) }
    var emailError = rememberSaveable { mutableStateOf<String?>(null) }
    var phoneError = rememberSaveable { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScreen(
        titleText = "User Creation",
        modifier = Modifier,
        navigationIcon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
        navigationOnClick = {
            navController.popBackStack()
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column {
                    Spacer(Modifier.height(50.dp))
                    Text(
                        "Create New User",
                        style = LocalTypography.current.customContentNotAvailableText,
                        fontWeight = FontWeight.Bold
                    )

                }
                Spacer(Modifier.height(100.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        ),
                        supportingText = {
                            nameError.value?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        shape = MaterialTheme.shapes.extraLarge,
                        label = {
                            Text(
                                "Enter Your Name",
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 30.dp,
                                end = 30.dp,
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "",
                            )
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        shape = MaterialTheme.shapes.extraLarge,
                        label = {
                            Text(
                                "Enter Your Email",
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        supportingText = {
                            emailError.value?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 30.dp,
                                end = 30.dp,
                                top = 10.dp,
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "",
                            )
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        shape = MaterialTheme.shapes.extraLarge,
                        label = {
                            Text(
                                "Enter Your Phone No.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        ),
                        supportingText = {
                            phoneError.value?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 30.dp,
                                end = 30.dp,
                                top = 10.dp,
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "",
                            )
                        }
                    )
                    val context = LocalContext.current
                    Spacer(Modifier.height(40.dp))
                    OutlinedButton(
                        onClick = {
                            if (isNameValid(
                                    name,
                                    nameError
                                ) && isEmailValid(
                                    email,
                                    emailError
                                ) && isValidPhone(
                                    phone,
                                    phoneError
                                )
                            ) {
                                val createdUser = CreatedUserDataClass(name, email, phone)
                                viewModel.createUser(createdUser) { success ->
                                    Toast.makeText(
                                        context,
                                        if (success) "User Successfully Created" else "User Creation Failed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier.size(width = 200.dp, height = 50.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        elevation = ButtonDefaults.buttonElevation(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = "Create User",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
    )
}

fun isNameValid(
    name: String,
    nameError: MutableState<String?>
): Boolean {
    if (name.isEmpty()) {
        nameError.value = "Field Cannot Be Empty"
        return false
    } else if (name.length > 30) {
        nameError.value = "Length Cannot be Greater Than 30"
        return false
    } else {
        nameError.value = null
        return true
    }
}

fun isEmailValid(
    email: String,
    emailError: MutableState<String?>
): Boolean {
    if (email.isBlank()) {
        emailError.value = "Field Cannot Be Empty"
        return false
    } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
        emailError.value = "Invalid Email"
        return false
    } else {
        emailError.value = null
        return true
    }
}

fun isValidPhone(
    phone: String,
    phoneError: MutableState<String?>
): Boolean {
    if (phone.isBlank()) {
        phoneError.value = "Field Cannot Be Empty"
        return false
    } else if (!Regex("^[0-9x\\-\\s]+$").matches(phone)) {
        phoneError.value = "Invalid Phone Number"
        return false
    } else {
        phoneError.value = null
        return true
    }
}