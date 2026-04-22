package com.example.exemple.payment.p2.printer

enum class SipagP2PrinterState(val code: Int) {
    NORMAL(1),
    PREPARING(2),
    NO_PAPER(4),
    OVERHEATED(5),
    COMMUNICATION_ERROR(3),
    LID_OPEN(6),
    CUTTER_ERROR(7),
    CUTTER_RECOVERED(8),
    NO_BLACK_MARK(9),
    UNKNOWN(-1);

    companion object {
        fun fromCode(code: Int?) = entries.firstOrNull { it.code == code } ?: UNKNOWN
    }
}
