package interfaces

import Enum.AESkeySize

interface IAES {

    fun encrypt(data: String): String

    fun decrypt(data: String): String

}
