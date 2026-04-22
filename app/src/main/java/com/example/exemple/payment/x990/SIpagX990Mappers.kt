package com.example.exemple.payment.x990

import com.example.exemple.payment.SipagTransactionResult
import com.example.exemple.payment.othrer.TipoCartaoEnum
import com.example.exemple.payment.othrer.TipoPagamento
import printx990.br.apix990.internal.domain.dto.CREDITTYPE
import printx990.br.apix990.internal.domain.dto.CreditInfo

fun SipagX990TransactionResult.toSipagTransactionResult() = SipagTransactionResult(
    resRrn = aid,
    resId = transactionID,
    resLabel = brand
)
fun tipoPagamentoToSipagX990PaymentType(type: TipoPagamento, installments: Int = 1): SipagX990PaymentType {
    return if (type.isPix) {
        SipagX990PaymentType.Pix
    } else {
        type.tipoCartao.toSipagX990PaymentType(installments)
    }
}

private fun TipoCartaoEnum.toSipagX990PaymentType(installments: Int = 1): SipagX990PaymentType = when(this) {
    TipoCartaoEnum.CREDITO_A_VISTA -> SipagX990PaymentType.Credit()
    TipoCartaoEnum.CREDITO_PARCELADO -> SipagX990PaymentType.Credit(CreditInfo(CREDITTYPE.PARCELADOLOJA.toString(), installments))
    TipoCartaoEnum.CREDITO_PARCELADO_EMISSOR -> SipagX990PaymentType.Credit(CreditInfo(CREDITTYPE.PARCELADOEMISSAOR.toString(), installments))
    TipoCartaoEnum.DEBITO -> SipagX990PaymentType.Debit
    TipoCartaoEnum.VOUCHER -> SipagX990PaymentType.Voucher
}