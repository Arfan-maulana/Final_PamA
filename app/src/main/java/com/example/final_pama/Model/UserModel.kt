package com.example.final_pama.Model

class UserModel (
    val user: User,
    val token: String
)

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
)