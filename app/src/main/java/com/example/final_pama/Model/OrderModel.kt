package com.example.final_pama.Model

data class OrderModel(
    val order: Order,
    val message: String
)
data class Order (
    val  id : Int,
    val user_id: Int,
    val data: String,
    val created_at: String,
    val update_at: String

)
