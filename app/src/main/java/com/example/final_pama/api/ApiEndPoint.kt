package com.example.final_pama.api

import android.provider.ContactsContract.CommonDataKinds.Email
import android.telecom.Call
import com.example.final_pama.Model.MovieModel
import com.example.final_pama.Model.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email")email: String, @Field("password")password: String): retrofit2.Call<UserModel>

    @FormUrlEncoded
    @GET("refresh")
    fun refresh(): retrofit2.Call<UserModel>

    @GET("movie")
    fun getmovie(): retrofit2.Call<MovieModel>
}