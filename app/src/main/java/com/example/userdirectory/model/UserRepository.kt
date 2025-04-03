package com.example.userdirectory.model

import android.util.Log
import retrofit2.Response

class UserRepository {
       private val apiServiceForJson = RetrofitInstance.jsonPlaceholderApi
       private val apiServiceForReqRes = RetrofitInstance.reqResApi
    suspend fun getUserList(): Response<List<FetchedUserDataClass>>{
            return apiServiceForJson.getUserData()
    }
    suspend fun createUser(user: CreatedUserDataClass): Response<CreatedUserDataClass> {
        Log.d("UserRepository", "Creating user: $user")
        return apiServiceForReqRes.createUser(user)
    }
}