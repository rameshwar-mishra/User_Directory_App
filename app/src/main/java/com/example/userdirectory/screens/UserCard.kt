package com.example.userdirectory.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.userdirectory.model.FetchedUserDataClass
import com.example.userdirectory.reusablecomposable.CustomTextWithLabel

@Composable
fun SampleUser(
    user : FetchedUserDataClass,
    onclick : () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .clickable{
                onclick()
            }
            .padding(bottom = 10.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                CustomTextWithLabel("Name: ", user.name)
                CustomTextWithLabel("Company: ", user.company.name)
                CustomTextWithLabel("Email: ", user.email)
            }
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                modifier = Modifier
                    .rotate(270f),
                contentDescription = ""
            )
        }


    }

}