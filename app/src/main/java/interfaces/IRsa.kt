package interfaces

import Enum.RSAkeySize

interface IRsa {

    fun generatekeys(bitSize: RSAkeySize)

    fun getPublicKeyComponent()

    fun getPrivateKeyComponent()

    fun encrypt(data: String): String

    fun decrypt(): String

    fun signKey(): String

}
