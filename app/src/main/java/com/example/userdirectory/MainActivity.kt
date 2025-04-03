package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.userdirectory.navigation.NavigationApp
import com.example.userdirectory.ui.theme.UserDirectoryTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserDirectoryTheme {
                NavigationApp()
            }
        }
    }
}


