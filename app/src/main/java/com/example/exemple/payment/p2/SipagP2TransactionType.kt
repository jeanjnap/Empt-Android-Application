package com.example.exemple.payment.p2


enum class SipagP2TransactionType(val code: Int, val description: String) {
    PAYMENT(1, "Payment"),
    CANCELLATION(2, "Cancellation (refund)"),
    CONFIGURATION(3, "Configuration"),
    PARAMETERIZATION(4, "Parameterization"),
    POS7_ACTIVATION(5, "POS7 Activation"),
    TELELOAD(6, "Teleload"),
    COMMUNICATION_TEST(7, "Communication Test"),
    CLOSING(8, "Closing"),
    DEACTIVATE_TERMINAL(9, "Deactivate Terminal"),
    SALES_REPORT(10, "Sales Report"),
    OPENING(11, "Opening"),
    SEND_STATISTICS(12, "Send Statistics"),
    CHANGE_PASSWORD(13, "Change Merchant Password"),
    CHECK_BALANCE(14, "Check Balance"),
    INSTALLMENT_QUERY(15, "Installment Query"),
    ENABLE_WIFI(16, "Enable WiFi"),
    DISABLE_WIFI(17, "Disable WiFi"),
    REPRINT(18, "Reprint"),
    RESEND_SMS(19, "Resend SMS"),
    REVERSAL(20, "Reversal"),
    PIX_REFUND(21, "Pix Refund"),
    PIX_QUERY(22, "Pix Query");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
