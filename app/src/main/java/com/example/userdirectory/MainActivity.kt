package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.userdirectory.screens.HomeScreenUserList
import com.example.userdirectory.ui.theme.UserDirectoryTheme
import com.example.userdirectory.viewModel.UserViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this)[UserViewModel::class.java]
            UserDirectoryTheme {
                HomeScreenUserList(viewModel)
            }
        }
    }
}


