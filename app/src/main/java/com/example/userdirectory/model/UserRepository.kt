package com.example.userdirectory.model

import retrofit2.Response

class UserRepository {
       private val apiService = RetrofitInstance.api
    suspend fun actualCalltoApi(): Response<List<FetchedUserDataClass>>{
            return apiService.getUserData()
    }
}