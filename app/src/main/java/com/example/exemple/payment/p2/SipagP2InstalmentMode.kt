package com.example.exemple.payment.p2

enum class SipagP2InstalmentMode(val code: Int, description: String) {
    STORE(1, "Parcelamento Loja"),
    ISSUER(2, "Parcelamento Emissor");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
