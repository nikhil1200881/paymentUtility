package com.payment.paymentutilities

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import interfaces.IConvertor
import java.util.*

class Convertor: IConvertor {
    override fun bcdToAscii(input: ByteArray): String {
        try{
            println("hexChars = $input")
            return if (input != null) {
                val hexArray = charArrayOf(
                    '0',
                    '1',
                    '2',
                    '3',
                    '4',
                    '5',
                    '6',
                    '7',
                    '8',
                    '9',
                    'A',
                    'B',
                    'C',
                    'D',
                    'E',
                    'F'
                )
                val hexChars = CharArray(input.size * 2) // Each byte has two hex characters (nibbles)
                var v: Int
                for (j in input.indices) {
                    v = input[j].toInt() and 0xFF // Cast bytes[j] to int, treating as unsigned value
                    hexChars[j * 2] = hexArray[v ushr 4] // Select hex character from upper nibble
                    hexChars[j * 2 + 1] = hexArray[v and 0x0F] // Select hex character from lower nibble
                }
                println("hexChars+ = $hexChars")
                hexToAscii(String(hexChars))
            } else ""
        }catch (ex: Exception){
            return ""
        }
    }

    override fun bcdToHex(input: ByteArray): String {
        try {
            println("hexChars = $input")
            return if (input != null) {
                val hexArray = charArrayOf(
                    '0',
                    '1',
                    '2',
                    '3',
                    '4',
                    '5',
                    '6',
                    '7',
                    '8',
                    '9',
                    'A',
                    'B',
                    'C',
                    'D',
                    'E',
                    'F'
                )
                val hexChars = CharArray(input.size * 2) // Each byte has two hex characters (nibbles)
                var v: Int
                for (j in input.indices) {
                    v = input[j].toInt() and 0xFF // Cast bytes[j] to int, treating as unsigned value
                    hexChars[j * 2] = hexArray[v ushr 4] // Select hex character from upper nibble
                    hexChars[j * 2 + 1] = hexArray[v and 0x0F] // Select hex character from lower nibble
                }
                println("hexChars+ = ${String(hexChars)}")
                String(hexChars)
            } else ""
        }catch (ex: Exception){
            return ""
        }
    }

    override fun asciiToHex(data: String): String {
        try {
            val chars: CharArray = data.toCharArray()
            val hex = StringBuffer()
            for (i in chars.indices) {
                hex.append(Integer.toHexString(chars[i].toInt()))
            }
            return hex.toString()
        }catch (ex: Exception){
            return ""
        }

    }

    override fun asciiToBcd(input: String): ByteArray {
        try {
            val result = ByteArray(input.length / 2)

            for (i in input.indices step 2) {
                val highNibble = Character.digit(input[i], 16)
                val lowNibble = Character.digit(input[i + 1], 16)

                if (highNibble == -1 || lowNibble == -1) {
                    throw IllegalArgumentException("Invalid input character in ASCII string")
                }

                result[i / 2] = ((highNibble shl 4) or lowNibble).toByte()
            }

            return result
        }catch (ex: Exception){
            return byteArrayOf()
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun base64Tohex(data: String): String {
        try {
            if(data.isNullOrBlank()){
                Log.d("Convertor","Do not pass empty string")
                return ""
            }
            return bcdToHex(Base64.getDecoder().decode(data))
        }catch (ex: Exception){
            println(ex)
            return ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun base64ToAscii(data: String): String {
        try {
            if(data.isNullOrBlank()){
                Log.d("Convertor","Do not pass empty string")
                return ""
            }
            return bcdToAscii(Base64.getDecoder().decode(data))
        }catch (ex: Exception){
            println(ex)
            return ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun asciiTobase64(data: String): String {
        try {
            if(data.isNullOrBlank()){
                Log.d("Convertor","Do not pass empty string")
                return ""
            }
            return bcdToAscii(Base64.getEncoder().encode(data.toByteArray()))
        }catch (ex: Exception){
            println(ex)
            return ""
        }
    }


    override fun hexToAscii(data: String): String {
        try {
            val output = StringBuilder("")
            var characters = 0
            while (characters < data.length - 1) {
                val str = data.substring(characters, characters + 2)
                output.append(str.toInt(16).toChar())
                characters += 2
            }
            return output.toString()
        }catch (ex: Exception){
            return ""
        }
    }
}
