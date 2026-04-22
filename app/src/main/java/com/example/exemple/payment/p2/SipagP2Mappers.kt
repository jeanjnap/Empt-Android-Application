package com.example.exemple.payment.p2

import com.example.exemple.payment.SipagTransactionResult
import com.example.exemple.payment.othrer.TipoCartaoEnum
import com.example.exemple.payment.othrer.TipoPagamento
import ger7.com.br.pos7api.ParamOut

fun ParamOut?.toSipagP2TransactionResult() = SipagTransactionResult(
    apiVersion = this?.apiVersion,
    pos7Version = this?.apiVersion,
    status = SipagP2Staus.Companion.fromCode(this?.status),
    config = SipagP2Config.fromCode(this?.config),
    license = SipagP2License.fromCode(this?.license),
    terminalId = this?.terminalId,
    merchantId = this?.merchantId,
    resId = this?.resId,
    resType = this?.resType,
    resProduct = SipagP2TransactionProduct.Companion.fromCode(this?.resProduct),
    response = SipagP2TransactionStatus.Companion.fromCode(this?.response),
    resAuthorization = this?.resAuthorization,
    resAmount = this?.resAmount,
    resInstallments = this?.resInstallments,
    resInstMode = SipagP2InstalmentMode.fromCode(this?.resInstMode),
    resStan = this?.resStan,
    resRrn = this?.resRrn,
    resTime = this?.resTime,
    resPrint = this?.resPrint,
    resTrack2 = this?.resTrack2,
    resAid = this?.resAid,
    resCardholder = this?.resCardholder,
    resPrefName = this?.resPrefName,
    resDisplay = this?.resDisplay,
    resLabel = this?.resLabel,
    errorCode = this?.resErrorCode,
    authorizationType = SipagP2AuthorizationType.fromCode(this?.authorizationType),
    cardEntryMode = SipagP2CardEntryMode.fromCode(this?.cardEntryMode),
    pan = this?.pan,
    expiryDate = this?.expiryDate,
    resPixId = this?.resPixId,
    resPixEndToEndId = this?.resPixEndToEndId
)

fun tipoPagamentoToSipagP2PaymentType(type: TipoPagamento, installments: Int = 1): SipagP2PaymentType {
    return if (type.isPix) {
        SipagP2PaymentType.PIX
    } else {
        type.tipoCartao.toSipagP2PaymentType(installments)
    }
}

private fun TipoCartaoEnum.toSipagP2PaymentType(installments: Int = 1): SipagP2PaymentType = when(this) {
    TipoCartaoEnum.CREDITO_A_VISTA -> SipagP2PaymentType.Credit()
    TipoCartaoEnum.CREDITO_PARCELADO -> SipagP2PaymentType.Credit(installments, SipagP2InstalmentMode.STORE)
    TipoCartaoEnum.CREDITO_PARCELADO_EMISSOR -> SipagP2PaymentType.Credit(installments, SipagP2InstalmentMode.ISSUER)
    TipoCartaoEnum.DEBITO -> SipagP2PaymentType.DEBIT
    TipoCartaoEnum.VOUCHER -> SipagP2PaymentType.VOUCHER
}