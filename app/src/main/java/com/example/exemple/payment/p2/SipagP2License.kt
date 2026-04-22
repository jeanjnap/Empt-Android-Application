package com.example.exemple.payment.p2

enum class SipagP2License(val code: Int, val description: String) {
    ACTIVE(1, "Ativa"),
    INACTIVE(0, "Inativa"),
    EXPIRED(99, "Expirada"),
    DISABLED(98, "Desativada"),
    BLOCKED(97, "Bloqueada"),
    DENIED(96, "Negada");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
