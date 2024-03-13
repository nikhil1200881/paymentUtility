package com.payment.paymentutilities

import Enum.*
import android.util.Log
import dataclass.Track2
import interfaces.IpaymnetUtility
import java.util.*

class PaymentUtility: IpaymnetUtility {
    val convertor = Convertor()
    override fun getCardType(panNumber: String): CardType {
        try {
            if(panNumber.isNullOrEmpty()){
                Log.d("PaymentUtility","getCardType() -> Please Enter Pan Number")
                return CardType.UNKNOWN
            }
            var firstCharacter = panNumber.substring(0,1)
            return when(firstCharacter){
                Constants.TWO,
                Constants.FIVE-> CardType.MASTER
                Constants.THREE,
                Constants.SIX-> CardType.RUPAY
                Constants.FOUR -> CardType.RUPAY
                else -> {CardType.UNKNOWN}
            }
        }catch (ex: Exception){
            Log.d("PaymentUtility","getCardType() -> please check input pan number")
            return CardType.UNKNOWN
        }
    }

    override fun getEntryMode(entryMode: EntryMode?, pinEntry: Boolean?): String {
       try {
          var entryMode =  when(entryMode){
               EntryMode.MANUAL -> "01"
               EntryMode.SWIPE  -> "02"
               EntryMode.INSERT -> "05"
               EntryMode.FALLBACK -> "80"
               EntryMode.MSD -> "91"
               EntryMode.CTLS -> "07"
               else -> "00"
           }
           entryMode += if (pinEntry!!) Constants.ONE else Constants.ZERO
           return entryMode

       }catch (ex: Exception){
           return ""
       }
        return ""
    }

    override fun isNeedPinEntry(cvmResult: String, entryMode: EntryMode?, serviceCode:String): String {
        try {
            if ((entryMode== EntryMode.CTLS) ||  (entryMode== EntryMode.INSERT ))
            {
                val parCVM = cvmResult.substring(0,2)
                val convertBinary =hexToBinary(parCVM)
                println("Enter in fun $convertBinary")
                if(convertBinary == "1000000")
                {
                    return CvmCondition.FAIL.stringValue
                }
                else if(convertBinary == "1000001")
                {
                    return CvmCondition.PIN_by_ICC.stringValue
                }
                else if (convertBinary == "1000010")
                {
                    return CvmCondition.Enc_PIN_Verfied_Online.stringValue
                }
                else if(convertBinary == "1000011")
                {
                    return CvmCondition.PIN_by_ICC_Signature.stringValue
                }
                else if (convertBinary == "1000100")
                {
                    return CvmCondition.Enc_PIN_by_ICC.stringValue
                }
                else if (convertBinary =="1000101")
                {
                    return CvmCondition.Enc_PIN_by_ICC_Signature.stringValue
                }
                else if (convertBinary == "1011110")
                {
                    return CvmCondition.Sign.stringValue
                }
                else if (convertBinary =="1011111")
                {
                    return CvmCondition.No_CVM_Req.stringValue
                }
                else if(cvmResult =="1111111")
                {
                    return CvmCondition.NO_CVM.stringValue
                }
                else
                {
                    throw IllegalArgumentException("No Valid CVM")
                }

            }
         if((entryMode== EntryMode.SWIPE))
         {
             val code = serviceCode.last().toString()
             println("last digit $code")
            if(code =="0" || code =="3" || code == "5" || code =="6" || code =="7")
            {
                return "PIN Pad Open"
            }
            return "PIN Not Required"
         }
        }catch (ex : Exception)
        {
             Log.e("Error","error $ex")
        }
        return " "
    }
    //
    fun hexToBinary(hex: String): String {
        val decimalValue = Integer.parseInt(hex, 16)
        return Integer.toBinaryString(decimalValue)
    }
    override fun generatePinBlock(panNumber: String, pin : String,pinBlockType: PinBlockType): String {

        try {
            if (pin.length < 4 || pin.length > 6) {
               // Log.e("error", "This is wrong  entry")
                throw IllegalArgumentException("PIN length must be between 4 and 6 digits")
            }
            var pinBlock = String.format("%s%d%s", 0, pin.length, pin)
            while (pinBlock.length != 16) {
                pinBlock += "F"
            }
            val cardLen = panNumber.length
            val pan = "0000" + panNumber.substring(cardLen - 13, cardLen - 1)
            return xorConvert(pinBlock, pan)
        } catch (ex: Exception) {
            Log.e("error", "Exception caught: ${ex.message}")
            return ""
        }
    }

    private fun xorConvert(pan: String, block: String): String {
        val chars = CharArray(pan.length)
        println("--length$chars")
        for (i in chars.indices) {
            //println(chars)
            chars[i] = toHex(fromHex(pan[i]) xor fromHex(block[i]))
            //println("${chars[i]}")
        }
        return String(chars).uppercase(Locale.getDefault())

    }
    private fun fromHex(c: Char): Int {
        if (c in '0'..'9') {
            println("$c")
            return c - '0'
        }
        if (c in 'A'..'F') {
            println("$c")
            println("fromHexCap${c - 'A' + 10}")
            return c - 'A' + 10
        }
        if (c in 'a'..'f') {
            println("fromHex${c - 'a' + 10}")
            return c - 'a' + 10
        }
        throw IllegalArgumentException()
    }

    private fun toHex(data: Int): Char {
        if (data in 0..15) {
            return "0123456789ABCDEF"[data]
        } else {
            throw IllegalArgumentException("Invalid data value: $data")
        }
    }


    override fun isRequiredSignatureOnReceipt(cvmResult: String, entryMode: EntryMode?): Boolean {
        TODO("Not yet implemented")
    }

    override fun parseTlvData(emvData: String): String {
        TODO("Not yet implemented")
    }

    override fun parseTlvData(emvData: ByteArray): String {
        TODO("Not yet implemented")
    }

    override fun getTransactionType(transactionType: TransactionType): String {
        TODO("Not yet implemented")
    }

    override fun checkNetworkType(): NetworkType? {
        TODO("Not yet implemented")
    }

    override fun parseTrack2(track2: String): Track2 {
        TODO("Not yet implemented")
    }

    override fun parseTrack3(track3: String): String {
        TODO("Not yet implemented")
    }

    override fun parseTrack1(track1: String): String {
        TODO("Not yet implemented")
    }

    override fun incrementUniqueNumber(incrementBy: Int?, number: String?): String? {
        TODO("Not yet implemented")
    }

    override fun changeDateFormat(dateFromISO: String, dateFormat: DateFormat, seprator: String): String? {
        TODO("Not yet implemented")
    }

    override fun changeTimeFormat(timeFromISO: String, timeFormar: TimeFormat?, seperator: String?): String? {
        TODO("Not yet implemented")
    }

    override fun getCurrentTime(timeFormat: TimeFormat?, seprator: String): String? {
        TODO("Not yet implemented")
    }

    override fun getCurrentDate(dateFormat: DateFormat, seperator: String?): String? {
        TODO("Not yet implemented")
    }

    override fun generateRandomNumber(): String {
        TODO("Not yet implemented")
    }

    override fun isCardExpired(expiryDate: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMaskedPan(panFirst: Int?, panLast: Int?): String {
        TODO("Not yet implemented")
    }

    override fun parseTVR(tvrValue: String?): String? {
        TODO("Not yet implemented")
    }

    override fun parseTVR(tvrValue: ByteArray?): String? {
        TODO("Not yet implemented")
    }

    override fun ttqParser(ttq: String?): String? {
        TODO("Not yet implemented")
    }

    override fun ttqParser(ttq: ByteArray?): String? {
        TODO("Not yet implemented")
    }

    override fun addationalTerminakCapParser(addationalTerminalCap: String?) {
        TODO("Not yet implemented")
    }

    override fun getResponseCodeMessage(resonseCode: String): String {
        TODO("Not yet implemented")
    }

    override fun getFormattedAmount(amount: String, deciamlPoint: Int, currencyCode: Int): String {
        TODO("Not yet implemented")
    }
    /* ----------------Inline Methods ---------------------*/

    fun String.isOnlineApproved() = this == Constants.APPROVED_RESPONSE_CODE

    fun String.toDecodeCVM():String? = ""


}
