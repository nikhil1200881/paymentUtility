package interfaces

import Enum.CardType
import Enum.DateFormat
import Enum.EntryMode
import Enum.NetworkType
import Enum.PinBlockType
import Enum.TimeFormat
import Enum.TransactionType
import dataclass.Track2
import java.util.Currency

interface IpaymnetUtility {

    fun getCardType(panNumber: String): CardType

    fun getEntryMode(entryMode: EntryMode?,pinEntry: Boolean?): String

    fun isNeedPinEntry(cvmResult: String, entryMode: EntryMode?,serviceCode:String): String

    fun generatePinBlock(panNumber: String,pin : String,pinBlockType: PinBlockType): String

    fun isRequiredSignatureOnReceipt(cvmResult: String,entryMode: EntryMode?): Boolean

    fun parseTlvData(emvData: String): String

    fun parseTlvData(emvData: ByteArray): String

    fun getTransactionType(transactionType: TransactionType): String

    fun checkNetworkType(): NetworkType?

    fun parseTrack2(track2: String):Track2

    fun parseTrack3(track3: String): String

    fun parseTrack1(track1: String): String

    fun incrementUniqueNumber(incrementBy: Int?, number: String?): String?

    fun changeDateFormat(dateFromISO: String, dateFormat: DateFormat, seprator: String): String?

    fun changeTimeFormat(timeFromISO: String, timeFormar: TimeFormat?,seperator: String?): String?

    fun getCurrentTime(timeFormat: TimeFormat?,seprator: String): String?

    fun getCurrentDate(dateFormat: DateFormat,seperator: String?): String?

    fun generateRandomNumber(): String
    
    fun isCardExpired(expiryDate: String): Boolean

    fun getMaskedPan(panFirst: Int?, panLast: Int?): String

    fun parseTVR(tvrValue: String?): String?

    fun parseTVR(tvrValue: ByteArray?): String?

    fun ttqParser(ttq: String?): String?

    fun ttqParser(ttq: ByteArray?): String?

    fun addationalTerminakCapParser(addationalTerminalCap: String?)

    fun getResponseCodeMessage(resonseCode: String): String

    fun getFormattedAmount(amount: String, deciamlPoint: Int, currencyCode: Int): String




}
