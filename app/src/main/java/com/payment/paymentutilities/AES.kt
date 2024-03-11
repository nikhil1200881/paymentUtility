package com.payment.paymentutilities

import Enum.AESkeySize
import Enum.AesMode
import interfaces.IAES

class AES(keySize: AESkeySize,key: String,mode: AesMode): IAES {

    override fun encrypt(data: String): String {
        TODO("Not yet implemented")
    }

    override fun decrypt(data: String): String {
        TODO("Not yet implemented")
    }
}
