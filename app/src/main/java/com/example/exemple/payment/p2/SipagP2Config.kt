package com.example.exemple.payment.p2

enum class SipagP2Config(val code: Int, val description: String) {
    NOT_CONFIGURED(0, "Não configurado"),
    CONFIGURED_NOT_PARAMETRIZED(1, "Configurado, porém não parametrizado"),
    READY(2, "Parametrizado e pronto para transacionar");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
