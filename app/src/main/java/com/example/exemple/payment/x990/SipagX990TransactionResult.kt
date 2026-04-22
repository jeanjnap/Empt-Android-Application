package com.example.exemple.payment.x990

data class SipagX990TransactionResult(
    val aid: String?,
    val amount: String?,
    val authorizationCode: String?,
    val brand: String?,
    val cardHolder: String?,
    val displayMessage: String?,
    val installmentType: String?,
    val installments: Int?,
    val label: String?,
    val mError: List<SipagX990Error?>,
    val product: String?,
    val receiptList: List<SipagX990Receipt?>?,
    val responseCode: Int?,
    val result: String?,
    val time: String?,
    val transactionID: String?
)
