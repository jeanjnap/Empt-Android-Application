package com.example.exemple.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.exemple.payment.SipagSaleManager
import com.example.exemple.payment.othrer.ClassificacaoTipoPagamentoEnum
import com.example.exemple.payment.othrer.TipoCartaoEnum
import com.example.exemple.payment.othrer.TipoPagamento
import com.example.exemple.ui.theme.ExempleTheme


@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val transactionManager = SipagSaleManager(context)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(
            onClick = {
                transactionManager.makeTransaction(
                    value = 10.0,
                    paymentType = TipoPagamento(
                        classificacao = ClassificacaoTipoPagamentoEnum.TP_CARTAO,
                        tipoCartao = TipoCartaoEnum.CREDITO_A_VISTA
                    ),
                    instalments = 1,
                    onSuccess = {
                        it?.status
                    },
                    onError = {
                        Toast.makeText(context, it ?: "erro", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            modifier = Modifier
        ) {
            Text(text = "Pay Now")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    ExempleTheme {
        PaymentScreen()
    }
}