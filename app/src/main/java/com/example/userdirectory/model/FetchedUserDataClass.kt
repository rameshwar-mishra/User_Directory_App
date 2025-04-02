package com.example.userdirectory.model

data class FetchedUserDataClass(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val address: Address, //automatic mapping by GSON of Company field
    val phone: String,
    val website: String,
    val company: Company  //automatic mapping by GSON of address field
)
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
)
data class Company(
    val name: String
)
