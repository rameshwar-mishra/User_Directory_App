package com.example.userdirectory.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.model.ApiState
import com.example.userdirectory.model.FetchedUserDataClass
import com.example.userdirectory.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private var _users = MutableStateFlow<ApiState<List<FetchedUserDataClass>>>(ApiState.Loading)
    val users: StateFlow<ApiState<List<FetchedUserDataClass>>> = _users
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery
    private val _filteredUsers = combine(_users, _searchQuery) { userState, query ->
        if (query.isBlank()) {
            userState
        } else {
            when (userState) {
                is ApiState.Success -> {
                    ApiState.Success(userState.data.filter { user ->
                        user.name.startsWith(query, ignoreCase = true)
                    })
                }

                else -> userState
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, ApiState.Loading)
    val filteredUsers: StateFlow<ApiState<List<FetchedUserDataClass>>> = _filteredUsers
    fun fetchUsersFromRepo() {
        viewModelScope.launch {
            try {
                val response = userRepository.actualCalltoApi()
                if (response.isSuccessful) {
                    _users.value = ApiState.Success(response.body() ?: emptyList())
                } else {
                    _users.value = ApiState.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                _users.value = ApiState.Error("Error Message: $e")
            }
        }
    }

    fun updateSearchQuery() {

    }
}

