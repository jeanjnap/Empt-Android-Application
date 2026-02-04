package com.example.exemple

import android.app.Activity
import android.content.Context
import android.util.Log
import printx990.br.apix990.internal.TransactionInfo
import printx990.br.apix990.internal.domain.callbacks.TransactionCallback
import printx990.br.apix990.internal.domain.dto.CREDITTYPE
import printx990.br.apix990.internal.domain.dto.CreditInfo
import printx990.br.apix990.internal.domain.dto.PRODUCT
import printx990.br.apix990.internal.domain.dto.TransactionData
import printx990.br.apix990.internal.domain.dto.TypeTransaction

class SicoobX990Manager(private val context: Context) {

    val transactionInfo = TransactionInfo()

    fun makeTransaction() {
        transactionInfo.initTransaction(
            context as Activity,
            TransactionData (
                product = PRODUCT.CREDITO,
                amount = "10,00",
                creditType = CreditInfo(CREDITTYPE.AVISTA.toString(), 0),
                typeTransaction = TypeTransaction.TRANSACTION,
                packageName = context.packageName
            ),
            object : TransactionCallback {
                override fun onTransactionResult(resultData: String) {
                    Log.i("SicoobX990Manager", "Transaction Result: $resultData")
                }
            }
        )
    }
}