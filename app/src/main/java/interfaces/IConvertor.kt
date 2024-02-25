package interfaces

import Enum.CoversionType

interface IConvertor {

    fun bcdToAscii(data: ByteArray):String

    fun bcdToHex(data: ByteArray):String

    fun asciiToHex(data: String):String

    fun asciiToBcd(data: String):ByteArray

    fun base64Tohex(data: String): String

    fun base64ToAscii(data:String): String

    fun asciiTobase64(data: String): String

    fun hexToAscii(data:String): String
}
