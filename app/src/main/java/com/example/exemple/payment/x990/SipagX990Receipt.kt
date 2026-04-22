package com.example.exemple.payment.x990

data class SipagX990Receipt(
    val isLogo: Boolean?,
    val isReprint: Boolean?,
    val lines: List<String?>?,
    val receiptOwnwer: Int?
)
