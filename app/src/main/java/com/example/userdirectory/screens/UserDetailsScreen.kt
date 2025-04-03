package com.example.userdirectory.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.userdirectory.reusablecomposable.BaseScreen
import com.example.userdirectory.reusablecomposable.CustomTextWithLabel
import com.example.userdirectory.ui.theme.LocalTypography
import com.example.userdirectory.viewModel.UserViewModel


@Composable
fun UserDetailsScreen(
    viewModel: UserViewModel,
    navController: NavController
) {
    val userDataClass = viewModel.selectedUser.collectAsState().value
    Log.d("UserDetailsScreen", "User Data: ${viewModel.selectedUser.collectAsState().value}")
    BaseScreen(
        titleText = "User Details",
        navigationIcon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
        content = {
            if (userDataClass == null) {
                Log.e("UserDetailsScreen", "No user found! ViewModel is returning null")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No User Found!",
                        style = LocalTypography.current.customContentNotAvailableText
                    )
                }
                return@BaseScreen
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 18.dp,
                            bottom = 8.dp
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 18.dp,
                            bottom = 8.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(40.dp)
                            )
                            Text(
                                text = userDataClass.name
                            )
                        }
                        HorizontalDivider(thickness = 1.dp)
                        CustomTextWithLabel(
                            "Username: ",
                            userDataClass.username,
                            LocalTypography.current.textStyleUserDetails
                        )
                        CustomTextWithLabel(
                            "Email: ",
                            userDataClass.email,
                            LocalTypography.current.textStyleUserDetails
                        )
                        CustomTextWithLabel(
                            "Phone: ",
                            userDataClass.phone,
                            LocalTypography.current.textStyleUserDetails
                        )
                        CustomTextWithLabel(
                            "Website: ",
                            userDataClass.website,
                            LocalTypography.current.textStyleUserDetails
                        )
                        CustomTextWithLabel(
                            "Address: ",
                            "${userDataClass.address.city}, " +
                                    "${userDataClass.address.street}," +
                                    " ${userDataClass.address.zipcode}",
                            LocalTypography.current.textStyleUserDetails
                        )
                    }

                }
            }

        },
        navigationOnClick = {
            navController.popBackStack()
        }
    )
}