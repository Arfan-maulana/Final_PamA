package com.example.final_pama

import android.app.Activity
import android.app.AlertDialog
import android.os.Message
import java.text.NumberFormat
import java.util.Locale

object Helper {
    var id : Int= 0
    var name : String = " "
    var username: String = ""
    var email: String = ""
    var token: String = ""
    var total: Double = 0.0

    val BASE_IMAGE = "http://192.168.77.7/bioskop_api/bioskop_api/public/images/"
    val BASE_URL = "http://192.168.77.7/bioskop_api/bioskop_api/public/api/"

    fun message(message: String, activity: Activity, action: Boolean = false){
        val alertDialog = AlertDialog.Builder(activity)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("Ok"){dialog, which ->
                if (action){
                    activity.finish()
                }
            }
        alertDialog.show()
    }
}

fun currencyFormat(price: Double): String{
    return NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        .format(price)
}