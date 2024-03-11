package com.payment.paymentutilities

import Enum.DesPadding
import Enum.InputType
import Enum.TdesMode
import interfaces.Itdes

class Tdes(key: String,iv: String, mode: TdesMode,padding: DesPadding,inputType: InputType): Itdes {
    override fun encrypt(data: String) {
        TODO("Not yet implemented")
    }

    override fun decrypt(data: String) {
        TODO("Not yet implemented")
    }
}
