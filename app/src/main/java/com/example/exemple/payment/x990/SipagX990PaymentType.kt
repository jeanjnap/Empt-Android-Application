package com.example.exemple.payment.x990

import printx990.br.apix990.internal.domain.dto.CREDITTYPE
import printx990.br.apix990.internal.domain.dto.CreditInfo
import printx990.br.apix990.internal.domain.dto.PRODUCT

sealed class SipagX990PaymentType(
    val product: PRODUCT,
    open val creditInfo: CreditInfo?
) {
    data class Credit(
        override val creditInfo: CreditInfo = CreditInfo(CREDITTYPE.AVISTA.toString(), 0)
    ): SipagX990PaymentType(PRODUCT.CREDITO, CreditInfo(CREDITTYPE.AVISTA.toString()))

    data object Debit : SipagX990PaymentType(PRODUCT.DEBITO, null)
    data object Voucher : SipagX990PaymentType(PRODUCT.VOUCHER, null)
    data object Pix : SipagX990PaymentType(PRODUCT.PIX, null)
}