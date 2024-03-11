package com.payment.paymentutilities

import Enum.DesPadding
import interfaces.Ides

class Des(key: String, iv: String,padding: DesPadding): Ides {
    override fun encrypt(data: String): String {
        TODO("Not yet implemented")
    }

    override fun decrypt(date: String): String {
        TODO("Not yet implemented")
    }


}
