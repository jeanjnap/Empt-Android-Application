package com.example.exemple.payment

import android.content.Context
import android.os.Build
import com.example.exemple.payment.othrer.TipoPagamento
import com.example.exemple.payment.p2.SipagP2PaymentType
import com.example.exemple.payment.p2.SipagP2bSaleManager
import com.example.exemple.payment.p2.tipoPagamentoToSipagP2PaymentType
import com.example.exemple.payment.x990.SipagX990SaleManager
import com.example.exemple.payment.x990.tipoPagamentoToSipagX990PaymentType

class SipagSaleManager(
    private val context: Context
) {
    private val model: String by lazy {
        Build.MODEL
    }
    private val p2bSaleManager: SipagP2bSaleManager by lazy {
        SipagP2bSaleManager(context)
    }

    private val x990SaleManager: SipagX990SaleManager by lazy {
        SipagX990SaleManager(context)
    }

    fun makeTransaction(
        value: Double,
        paymentType: TipoPagamento,
        instalments: Int,
        onSuccess: (result: SipagTransactionResult?) -> Unit,
        onError: (error: String?) -> Unit,
        printReceipt: Boolean = true
    ) {
        if (model == SipagTerminalModel.P2.model) {
            p2bSaleManager.makeTransaction(
                id = generateTransactionId(),
                value = value,
                paymentType = tipoPagamentoToSipagP2PaymentType(paymentType, instalments),
                onSuccess = onSuccess,
                onError = onError,
                printReceipt = printReceipt
            )
        } else {
            x990SaleManager.makeTransaction(
                paymentType = tipoPagamentoToSipagX990PaymentType(paymentType, instalments),
                amount = value,
                onSuccess = onSuccess,
                onError = onError,
                printReceipt = printReceipt
            )
        }
    }

    fun refund(
        refoundId: String? = null,
        value: Double? = null,
        paymentType: SipagP2PaymentType? = null,
        printReceipt: Boolean = true,
        onSuccess: (result: SipagTransactionResult) -> Unit = {},
        onError: (error: String?) -> Unit = {}
    ) {
        if (model == SipagTerminalModel.P2.model) {
            p2bSaleManager.refund(
                id = generateTransactionId(),
                value = value,
                paymentType = paymentType,
                refoundId = refoundId,
                onSuccess = onSuccess,
                onError = onError,
                printReceipt = printReceipt
            )
        } else {
            x990SaleManager.refund(
                onSuccess = onSuccess,
                onError = onError,
                printReceipt = printReceipt
            )
        }
    }


    fun generateTransactionId(): String {
        val timestamp = System.currentTimeMillis().toString()
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val hash = timestamp.hashCode()
        fun toBase62(num: Int): String {
            var n = if (num < 0) -num else num
            val base62 = StringBuilder()

            while (n > 0) {
                base62.append(chars[n % 62])
                n /= 62
            }

            return base62.toString().padEnd(12, 'A')
        }

        val base62Hash = toBase62(hash)
        return base62Hash.take(12)
    }
}