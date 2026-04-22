package com.example.exemple.payment.p2

enum class SipagP2AuthorizationType(val code: Int, val description: String) {
    NOT_APPLICABLE(0, "Não se aplica"),
    ONLINE(1, "Online"),
    OFFLINE(2, "Offline");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
