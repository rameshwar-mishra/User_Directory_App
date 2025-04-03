package com.example.userdirectory.navigation

sealed class Routes(val route: String) {
    object UserListScreen : Routes("UserListScreen")
    object UserDetailsScreen : Routes("UserDetailsScreen")
    object UserCreationScreen : Routes("UserCreationScreen")

}