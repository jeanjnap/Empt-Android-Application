package com.example.exemple.payment.p2

enum class SipagP2CardEntryMode(val code: Int, val description: String) {
    NONE(0, "Nenhum"),
    MAGNETIC_STRIPE(1, "Tarja magnética"),
    EMV_CHIP(2, "Chip EMV"),
    MANUAL_ENTRY(3, "PAN digitado"),
    CONTACTLESS_MIFARE(4, "Contactless Mifare"),
    CONTACTLESS_EMV(5, "Contactless EMV");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
