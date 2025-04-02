package com.example.userdirectory.reusablecomposable

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.userdirectory.ui.theme.UserDirectoryTheme

@Composable
fun CustomTextWithLabel(
    labelValue: String,
    anchoredText: String,
) {
    Row {
        Text(
            text = labelValue,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = anchoredText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    titleText: String,
    navigationIcon: ImageVector? = null
) {
    UserDirectoryTheme {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    titleText,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            navigationIcon = {
                navigationIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = ""
                    )
                }
            },
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                actionIconContentColor = MaterialTheme.colorScheme.background
            ),

            )
    }

}

