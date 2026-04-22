package com.example.exemple.payment.p2

enum class SipagP2Staus(val code: Int, val description: String) {
    READY(0, "Pronto para transacionar"),
    NOT_READY(1, "não configurado, não parametrizado, não instalado, sem licença válida ou com defeito interno");

    companion object {
        fun fromCode(code: Int?) = entries.find { it.code == code }
    }
}
