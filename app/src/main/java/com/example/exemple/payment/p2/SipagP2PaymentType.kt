package com.example.exemple.payment.p2

import com.example.exemple.payment.othrer.cast

sealed class SipagP2PaymentType(val product: SipagP2TransactionProduct) {
    fun getNumberOfInstallments(): Int = this.cast<Credit>()?.installments ?: 1
    fun getInstallmentMode(): Int? = this.cast<Credit>()?.instalmentMode?.code

    data class Credit(
        val installments: Int = 1,
        val instalmentMode: SipagP2InstalmentMode = SipagP2InstalmentMode.STORE
    ) : SipagP2PaymentType(SipagP2TransactionProduct.CREDIT)

    data object DEBIT : SipagP2PaymentType(SipagP2TransactionProduct.DEBIT)
    data object VOUCHER : SipagP2PaymentType(SipagP2TransactionProduct.VOUCHER)
    data object PIX : SipagP2PaymentType(SipagP2TransactionProduct.PIX)
}
