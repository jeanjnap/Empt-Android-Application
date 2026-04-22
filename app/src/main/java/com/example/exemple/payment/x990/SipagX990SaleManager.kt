package com.example.exemple.payment.x990

import android.app.Activity
import android.content.Context
import com.example.exemple.payment.SipagTransactionResult
import com.example.exemple.payment.othrer.optionalObjectOf
import printx990.br.apix990.internal.TransactionInfo
import printx990.br.apix990.internal.domain.callbacks.TransactionCallback
import printx990.br.apix990.internal.domain.dto.TransactionData
import printx990.br.apix990.internal.domain.dto.TypeTransaction
import java.util.Locale

class SipagX990SaleManager(private val context: Context) {

    val transactionInfo = TransactionInfo()

    fun makeTransaction(
        paymentType: SipagX990PaymentType,
        amount: Double,
        onSuccess: (result: SipagTransactionResult?) -> Unit,
        onError: (error: String?) -> Unit,
        printReceipt: Boolean
    ) {
        transactionInfo.initTransaction(
            context as Activity,
            TransactionData(
                product = paymentType.product,
                amount = amount.formatValue(),
                creditType = paymentType.creditInfo,
                typeTransaction = TypeTransaction.TRANSACTION,
                packageName = context.packageName,
                receipt = printReceipt
            ),
            object : TransactionCallback {
                override fun onTransactionResult(resultData: String) {
                    handleTransactionResult(
                        resultData = resultData,
                        isRefund = false,
                        onSuccess = onSuccess,
                        onError = onError
                    )
                }
            }
        )
    }

    fun refund(
        onSuccess: (result: SipagTransactionResult) -> Unit,
        onError: (String?) -> Unit,
        printReceipt: Boolean
    ) {
        transactionInfo.initTransaction(
            context as Activity,
            TransactionData (
                product = null,
                amount = null,
                creditType = null,
                typeTransaction = TypeTransaction.CANCELTRANSACTION,
                packageName = context.packageName,
                receipt = printReceipt
            ),
            object : TransactionCallback {
                override fun onTransactionResult(resultData: String) {
                    handleTransactionResult(
                        resultData = resultData,
                        isRefund = true,
                        onSuccess = onSuccess,
                        onError = onError
                    )
                }
            }
        )
    }

    private fun handleTransactionResult(
        resultData: String,
        isRefund: Boolean,
        onSuccess: (result: SipagTransactionResult) -> Unit,
        onError: (String?) -> Unit,
    ) {
        val res = resultData.optionalObjectOf<SipagX990TransactionResult>()
        if (res?.result == APPROVED) {
            onSuccess(res.toSipagTransactionResult())
        } else {
            onError(
                if (isRefund) {
                    res?.mError?.firstOrNull()?.mErrorDescription
                } else {
                    res?.result
                }
            )
        }
    }

    private fun Double.formatValue(): String {
        return String.format(Locale.getDefault(),FORMAT, this).replace(DOT, COMMA)
    }

    private companion object {
        const val APPROVED = "APPROVED"
        const val FORMAT = "%.2f"
        const val DOT = "."
        const val COMMA = ","
    }
}
