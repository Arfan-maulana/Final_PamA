package com.example.final_pama.Model

data class SeatModel(
    val seats: List<Seat>
)
data class Seat(
    val id : Int,
    val status: String
)
