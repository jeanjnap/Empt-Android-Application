package com.example.exemple.payment.x990

data class SipagX990Error(
    val mErrorCode: String?,
    val mErrorDescription: String?,
    val mErrorType: String?,
    val mPOS7ErrorCode: Int?,
)
