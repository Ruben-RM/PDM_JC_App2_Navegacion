package com.example.pdm_jc_app2_navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun ThirdScreen(navController: NavController, numeroApostado: Int, numeroIntentos: Int)
{
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
    {
        TSBody(navController, Modifier.align(Alignment.Center), numeroApostado, numeroIntentos)
    }
}

@Composable
fun TSBody(navController: NavController, modifier: Modifier, numApostado: Int, numIntentos: Int)
{
    var randNumero by rememberSaveable { mutableStateOf(Random.nextInt(1, 11)) }
    var isRetryEnable by rememberSaveable { mutableStateOf(false) }

    isRetryEnable = numIntentos > 0

    Column(
        modifier = modifier
    )
    {
        Apuesta(Modifier.align(Alignment.CenterHorizontally), numApostado, numIntentos)
        Spacer(Modifier.size(16.dp))
        Resultado(Modifier.align(Alignment.CenterHorizontally), randNumero, numApostado)
        Spacer(Modifier.size(16.dp))
        BotonRetry(navController, isRetryEnable, numApostado, numIntentos)
        Spacer(Modifier.size(16.dp))
        BotonAtras(navController)
    }
}

@Composable
fun Apuesta(modifier: Modifier, numApostado: Int, numIntentos: Int)
{
    Text(
        text = "Número Apostado: $numApostado",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
    Text(
        text = "Número de Intentos: $numIntentos",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun Resultado(modifier: Modifier, numResultado: Int, numApostado: Int)
{
    val texto =
        if(numResultado == numApostado)
            "¡Acertaste! El número correcto era $numResultado"
        else
            "¡Fallaste! El número correcto era $numResultado"

    Text(
        text = texto,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun BotonRetry(navController: NavController, isRetryEnable: Boolean, numApostado: Int, numIntentos: Int)
{
    Button(
        onClick = {
            navController.navigate(Routes.Pantalla3.createRoute(numApostado, numIntentos - 1))
        },
        enabled = isRetryEnable,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ){
        Text( text = "Generar otro número" )
    }
}