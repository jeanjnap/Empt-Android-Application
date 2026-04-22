package com.example.exemple

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exemple.payment.SipagSaleManager
import com.example.exemple.payment.othrer.ClassificacaoTipoPagamentoEnum
import com.example.exemple.payment.othrer.TipoCartaoEnum
import com.example.exemple.payment.othrer.TipoPagamento
import com.example.exemple.ui.screens.Destinations
import com.example.exemple.ui.screens.IntegrationOptionsScreen
import com.example.exemple.ui.screens.PaymentScreen
import com.example.exemple.ui.theme.ExempleTheme
import com.example.exemple.ui.utils.CustomNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExempleTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    CustomNavHost(
                        navController = navController,
                        startDestination = Destinations.OPTIONS_SCREEN
                    ) {
                        composable(route = Destinations.OPTIONS_SCREEN) {
                            IntegrationOptionsScreen(
                                modifier = Modifier.padding(paddingValues),
                                navController = navController
                            )
                        }

                        composable(route = Destinations.PAYMENT_SCREEN) {
                            PaymentScreen(modifier = Modifier.padding(paddingValues))
                        }
                    }
                }
            }
        }
    }
}
