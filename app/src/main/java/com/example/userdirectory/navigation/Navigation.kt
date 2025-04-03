package com.example.userdirectory.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.userdirectory.screens.CreateUserScreen
import com.example.userdirectory.screens.HomeScreenUserList
import com.example.userdirectory.screens.UserDetailsScreen
import com.example.userdirectory.viewModel.UserViewModel

@Composable
fun NavigationApp() {
    val viewModel: UserViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Routes.UserListScreen.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it })
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it })
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it })
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it })
        }
    ) {
        composable(Routes.UserListScreen.route) {
            HomeScreenUserList(viewModel, navController)
        }
        composable(Routes.UserDetailsScreen.route) {
            UserDetailsScreen(viewModel, navController)
        }
        composable(Routes.UserCreationScreen.route) {
            CreateUserScreen(viewModel, navController)
        }
    }
}