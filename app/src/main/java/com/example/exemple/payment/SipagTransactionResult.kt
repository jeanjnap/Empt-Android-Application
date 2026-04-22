package com.example.exemple.payment

import com.example.exemple.payment.p2.SipagP2AuthorizationType
import com.example.exemple.payment.p2.SipagP2CardEntryMode
import com.example.exemple.payment.p2.SipagP2Config
import com.example.exemple.payment.p2.SipagP2InstalmentMode
import com.example.exemple.payment.p2.SipagP2License
import com.example.exemple.payment.p2.SipagP2Staus
import com.example.exemple.payment.p2.SipagP2TransactionProduct
import com.example.exemple.payment.p2.SipagP2TransactionStatus

data class SipagTransactionResult(
    val apiVersion: String? = null,
    val pos7Version: String? = null,
    val status: SipagP2Staus? = null,
    val config: SipagP2Config? = null,
    val license: SipagP2License? = null,
    val terminalId: String? = null,
    val merchantId: String? = null,
    val resId: String? = null,
    val resType: String? = null,
    val resProduct: SipagP2TransactionProduct? = null,
    val response: SipagP2TransactionStatus? = null,
    val resAuthorization: String? = null,
    val resAmount: String? = null,
    val resInstallments: Int? = null,
    val resInstMode: SipagP2InstalmentMode? = null,
    val resStan: String? = null,
    val resRrn: String? = null,
    val resTime: String? = null,
    val resPrint: String? = null,
    val resTrack2: String? = null,
    val resAid: String? = null,
    val resCardholder: String? = null,
    val resPrefName: String? = null,
    val resDisplay: String? = null,
    val resLabel: String? = null,
    val errorCode: Int? = null,
    val authorizationType: SipagP2AuthorizationType? = null,
    val cardEntryMode: SipagP2CardEntryMode? = null,
    val pan: String? = null,
    val expiryDate: String? = null,
    val resPixId: String? = null,
    val resPixEndToEndId: String? = null
)