package com.example.final_pama.Model

data class TicketModel(
    val ticket: List<Ticket>
)

data class Ticket(
    val movie_tittle: String,
    val movie_price: Double,
    val movie_image: String,
    val cinema: String,
    val seat: Int,
    val date: String,
    val start_time: String,
    val end_time: String,
    val status: String,
)