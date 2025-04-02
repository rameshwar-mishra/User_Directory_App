package com.example.userdirectory.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.userdirectory.reusablecomposable.CustomTextWithLabel

@Preview(showSystemUi = true)
@Composable
fun UserDetailsScreen() {
    Column{
        CustomTextWithLabel("name","newName")
        CustomTextWithLabel("name","newName")
        CustomTextWithLabel("name","newName")
        CustomTextWithLabel("name","newName")
        CustomTextWithLabel("name","newName")
        CustomTextWithLabel("name","newName")
    }

}