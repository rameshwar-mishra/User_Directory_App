package com.example.userdirectory.model

data class FetchedUserDataClass(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
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

data class CreatedUserDataClass(
    val name : String,
    val email : String,
    val phone : String
)