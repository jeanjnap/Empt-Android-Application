package com.example.exemple.payment.p2

enum class SipagP2TransactionProduct(val code: Int, val description: String) {
    CREDIT(1, "Credit"),
    DEBIT(2, "Debit"),
    VOUCHER(4, "Voucher"),
    PIX(8, "Pix");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
