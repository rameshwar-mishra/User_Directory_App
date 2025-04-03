package com.example.userdirectory.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiInterface {
    @GET("users")
   suspend fun getUserData() : Response<List<FetchedUserDataClass>>
   @POST("users")
   suspend fun createUser(@Body createdUser : CreatedUserDataClass) : Response<CreatedUserDataClass>
}