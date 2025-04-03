package com.example.userdirectory.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.model.Address
import com.example.userdirectory.model.ApiState
import com.example.userdirectory.model.Company
import com.example.userdirectory.model.CreatedUserDataClass
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
    private var _createUserResponse = MutableStateFlow<ApiState<CreatedUserDataClass>?>(null)
    val createUserResponse: StateFlow<ApiState<CreatedUserDataClass>?> = _createUserResponse
    private var _users = MutableStateFlow<ApiState<List<FetchedUserDataClass>>>(ApiState.Loading)
    val users: StateFlow<ApiState<List<FetchedUserDataClass>>> = _users

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery

    private val _selectedUser = MutableStateFlow<FetchedUserDataClass?>(null)
    val selectedUser: StateFlow<FetchedUserDataClass?> = _selectedUser

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
                val response = userRepository.getUserList()
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

    fun setSelectedUser(user: FetchedUserDataClass) {
        Log.d("UserViewModel", "Setting selected user: ${user.name}")
        _selectedUser.value = user
    }

    fun createUser(createdUser: CreatedUserDataClass,onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                val response = userRepository.createUser(createdUser)
                if(response.isSuccessful){
                    response.body()?.let { createdUser ->
                        val newUser = FetchedUserDataClass(
                            id = "Data Not Available",
                            name = createdUser.name,
                            username = "Data Not Available",
                            email = createdUser.email,
                            address = Address(
                                street = "Data Not Available",
                                suite = "",
                                city = "",
                                zipcode = ""
                            ),
                            phone = createdUser.phone,
                            website = "Data Not Available",
                            company = Company(
                                name = "XYZ Company"
                            )
                        )

                        _users.value = when(val currentState = _users.value){
                            is ApiState.Success -> {
                                ApiState.Success(currentState.data + newUser)
                            }
                            else -> ApiState.Success(listOf(newUser))
                        }
                        onResult(true)
                    } ?: onResult(false)
                    _createUserResponse.value = ApiState.Success(response.body() ?: CreatedUserDataClass("Data Not Found","Data Not Found","Data Not Found"))
                }
                else{
                    _createUserResponse.value = ApiState.Error(response.errorBody().toString())
                    onResult(false)
                }
            }
            catch (e: Exception){
                _createUserResponse.value = ApiState.Error("Error Message: $e")
                }
        }
    }
}
