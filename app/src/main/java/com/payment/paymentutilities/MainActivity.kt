package com.payment.paymentutilities

import Enum.EntryMode
import Enum.PinBlockType
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
        val convertor = Convertor()
        val paymentUtility = PaymentUtility()
        button = findViewById(R.id.button_id)
        button!!.setOnClickListener {
            Log.e("Convertor",convertor.asciiTobase64("nikhil"))
            Log.e("Convertor",convertor.base64Tohex("bmlraGls"))
            Log.e("Convertor",convertor.base64ToAscii("bmlraGls"))
            Log.e("needPin",
                paymentUtility.isNeedPinEntry("430300", EntryMode.SWIPE,"")
            )
            //Log.e("BinaryValue",convertor.textToBinary("430300"))
            //Log.e("BinaryValue",convertor.textToBinary("Ramesh"))
            Log.e("PinBlock",paymentUtility.generatePinBlock("5318491024720994", "6849",PinBlockType.GENERAL))
        }

    }
}
