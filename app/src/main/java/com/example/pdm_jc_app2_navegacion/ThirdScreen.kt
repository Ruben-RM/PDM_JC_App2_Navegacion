package com.example.pdm_jc_app2_navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ThirdScreen(navController: NavController, numeroApostado: Float, numeroIntentos: String)
{
    val numApostado: Int = numeroApostado.toInt()
    val numIntentos: Int = numeroIntentos.toInt()

    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
    {
        TSBody(navController, Modifier.align(Alignment.Center), numApostado, numIntentos)
    }
}

@Composable
fun TSBody(navController: NavController, modifier: Modifier, numApostado: Int, numIntentos: Int)
{
    Column(
        modifier = modifier
    )
    {
        Text("Numero Apostado: $numApostado")

        Text("Numero Intentos: $numIntentos")
    }
}