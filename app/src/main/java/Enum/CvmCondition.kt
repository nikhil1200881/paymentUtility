package Enum

enum class CvmCondition(val stringValue: String) {
    FAIL("Fail CVM processing"),
    PIN_by_ICC("Plaintext PIN verification performed by ICC"),
    Enc_PIN_Verfied_Online("Enciphered PIN verified online"),
    PIN_by_ICC_Signature("Plaintext PIN verification performed by ICC and Signature"),
    Enc_PIN_by_ICC("Enciphered PIN verification performed by ICC"),
    Enc_PIN_by_ICC_Signature("Enciphered PIN verification performed by ICC and Signatur"),
    Sign("Signature"),
    No_CVM_Req("No CVM required"),
    NO_CVM("No CVM performed")

}