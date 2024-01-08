package com.example.final_pama.api

import android.provider.ContactsContract.CommonDataKinds.Email
import android.telecom.Call
import com.example.final_pama.Model.MovieModel
import com.example.final_pama.Model.OrderModel
import com.example.final_pama.Model.ResponseModel
import com.example.final_pama.Model.SeatModel
import com.example.final_pama.Model.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email")email: String, @Field("password")password: String): retrofit2.Call<UserModel>

    @FormUrlEncoded
    @GET("refresh")
    fun refresh(): retrofit2.Call<UserModel>

    @GET("movie")
    fun getmovie(): retrofit2.Call<MovieModel>

    @GET("movie/{id}")
    fun showMovie(@Path("id")id: Int ): retrofit2.Call<MovieModel>

    @GET("seat")
    fun getSeat(@Query("movie_schedule_id") id: Int, @Query("screening_date")
    date: String): retrofit2.Call<SeatModel>

    @POST("order")
    fun createOrder(): retrofit2.Call<OrderModel>

    @FormUrlEncoded
    @POST("order/detail")
    fun createdOrderDetail(@Field("order_id") order: Int, @Field
        ("movie_schedule_id") schedulee: Int, @Field("seat_id") seat: Int, @Field
                               ("date_screening") date: String): retrofit2.Call<ResponseModel>
}