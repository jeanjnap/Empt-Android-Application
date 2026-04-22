package com.example.exemple.payment.othrer

data class TipoPagamento(
    val classificacao: ClassificacaoTipoPagamentoEnum,
    val tipoCartao: TipoCartaoEnum,
    val isPix: Boolean = false
)