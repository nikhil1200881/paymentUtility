package com.payment.paymentutilities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var convertor = Convertor()
        button = findViewById(R.id.button_id)
        button!!.setOnClickListener {
            Log.e("Convertor",convertor.asciiTobase64("nikhil"))
            Log.e("Convertor",convertor.base64Tohex("bmlraGls"))
            Log.e("Convertor",convertor.base64ToAscii("bmlraGls"))

        }

    }
}
