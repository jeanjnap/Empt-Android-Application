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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.exemple.payment.SipagSaleManager
import com.example.exemple.payment.othrer.ClassificacaoTipoPagamentoEnum
import com.example.exemple.payment.othrer.TipoCartaoEnum
import com.example.exemple.payment.othrer.TipoPagamento
import com.example.exemple.ui.theme.ExempleTheme


@Composable
fun IntegrationOptionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AsyncImage(
            model = "https://developer.android.com/static/images/home/gemma-4-android-lockup.png?hl=pt-br",
            contentDescription = null,
        )
        Button(
            onClick = {
                navController.navigate(Destinations.PAYMENT_SCREEN)
            },
            modifier = Modifier
        ) {
            Text(text = "Pagamentos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntegrationOptionsScreenPreview() {
    ExempleTheme {
        IntegrationOptionsScreen(
            navController = rememberNavController()
        )
    }
}