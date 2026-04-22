package com.example.exemple.payment.p2

enum class SipagP2TransactionStatus(val code: Int, val description: String) {
    APPROVED(0, "Transaction approved"),
    DENIED(1, "Transaction denied (by authorizer, card, or POS)"),
    CANCELED(2, "Transaction canceled by user or keyboard timeout"),
    FAILED(3, "Transaction failed (communication error, card issues, or exception in flow)"),
    INVALID_STATE(4, "POS7 in invalid state (not configured/parameterized or without valid license)"),
    UNAVAILABLE(5, "Transaction unavailable in this POS7 version"),
    PIX_PROCESSING(6, "PIX payment processing");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
