package com.payment.paymentutilities

import Enum.CardType
import Enum.DateFormat
import Enum.EntryMode
import Enum.NetworkType
import Enum.PinBlockType
import Enum.TimeFormat
import Enum.TransactionType
import android.util.Log
import dataclass.Track2
import interfaces.IpaymnetUtility

class PaymentUtility: IpaymnetUtility {
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

    override fun isNeedPinEntry(cvmResult: String, entryMode: EntryMode?): Boolean {
        TODO("Not yet implemented")
    }

    override fun generatePinBlock(panNumber: String, pinBlockType: PinBlockType): String {
        TODO("Not yet implemented")
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
