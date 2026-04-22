package com.example.exemple.payment.p2

import android.content.Context
import com.example.exemple.payment.SipagTransactionResult
import com.example.exemple.payment.othrer.toStringOnlyNumbers
import ger7.com.br.pos7api.POS7API
import ger7.com.br.pos7api.ParamIn
import ger7.com.br.pos7api.ParamOut

class SipagP2bSaleManager(context: Context) {

    private val posApi: POS7API  = POS7API(context)

    fun makeTransaction(
        id: String,
        value: Double,
        paymentType: SipagP2PaymentType,
        onSuccess: (result: SipagTransactionResult) -> Unit,
        onError: (error: String?) -> Unit,
        printReceipt: Boolean = true
    ) {
        val paramIn = ParamIn().apply {
            trsId = id
            trsAmount = value.toStringOnlyNumbers()
            trsType = SipagP2TransactionType.PAYMENT.code
            trsProduct = paymentType.product.code
            trsInstallments = paymentType.getNumberOfInstallments()
            merchantPwd = false
            isTrsReceipt = printReceipt
            paymentType.getInstallmentMode()?.let { installmentMode ->
                trsInstMode = installmentMode
            }
        }

        process(paramIn, onSuccess, onError)
    }

    fun refund(
        id: String,
        value: Double?,
        paymentType: SipagP2PaymentType?,
        refoundId: String?,
        onSuccess: (result: SipagTransactionResult) -> Unit,
        onError: (String?) -> Unit,
        printReceipt: Boolean
    ) {
        val paramIn = ParamIn().apply {
            this.trsType = SipagP2TransactionType.CANCELLATION.code
            this.merchantPwd = false
            this.trsId = id
            value?.let { this.trsAmount = it.toStringOnlyNumbers() }
            paymentType?.product?.code?.let { this.trsProduct = it }
            this.isTrsReceipt = printReceipt
            this.trsRefundId = refoundId
        }

        process(paramIn, onSuccess, onError)
    }

    private fun process(
        paramIn: ParamIn,
        onSuccess: (result: SipagTransactionResult) -> Unit,
        onError: (error: String?) -> Unit
    ) {
        posApi.processTransaction(
            paramIn,
            object : POS7API.Pos7apiCallback {
                override fun onResult(paramOut: ParamOut?) {
                    paramOut?.let {
                        val transactionStatus = SipagP2TransactionStatus.Companion.fromCode(it.response)
                        val isTransactionApproved = transactionStatus?.code == SipagP2TransactionStatus.APPROVED.code

                        if (isTransactionApproved) {
                            onSuccess(paramOut.toSipagP2TransactionResult())
                        } else {
                            onError(paramOut.resDisplay)
                        }
                    } ?: run {
                        onError(null)
                    }
                }
            }
        )
    }
}
