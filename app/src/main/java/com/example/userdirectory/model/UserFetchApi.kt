package com.example.userdirectory.model

import retrofit2.Response
import retrofit2.http.GET

interface UserApiInterface {
    @GET("users")
   suspend fun getUserData() : Response<List<FetchedUserDataClass>>
}