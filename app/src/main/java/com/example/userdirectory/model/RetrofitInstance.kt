package com.example.userdirectory.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val jsonPlaceholder_URL = "https://jsonplaceholder.typicode.com/"
    private const val reqRes_URL = "https://reqres.in/api/"
    val jsonPlaceholderApi : UserApiInterface by lazy {
            Retrofit.Builder()
            .baseUrl(jsonPlaceholder_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiInterface::class.java)
    }
    val reqResApi : UserApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(reqRes_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiInterface::class.java)
    }
}
