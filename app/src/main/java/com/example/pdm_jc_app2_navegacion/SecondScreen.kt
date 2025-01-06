package com.example.pdm_jc_app2_navegacion

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController, user: String)
{
    Box(
        Modifier
        .fillMaxSize()
        .padding(8.dp)
    )
    {
        SSBody(navController, Modifier.align(Alignment.Center), user)
    }
}

@Composable
fun SSBody (navController: NavController, modifier: Modifier, user: String)
{
    var sliderPosition by rememberSaveable { mutableFloatStateOf(1f) }
    var numIntentos by rememberSaveable { mutableStateOf("") }
    var isPlayEnable by rememberSaveable { mutableStateOf(false) }

    isPlayEnable = (numIntentos != "") and (numIntentos != "0") and (numIntentos != "00")

    Column(
        modifier = modifier
    ) {
        Saludo(Modifier.align(Alignment.CenterHorizontally), user)
        Spacer(modifier = Modifier.size(16.dp))
        Deslizador(sliderPosition) { sliderPosition = it }
        Spacer(modifier = Modifier.size(16.dp))
        NumeroIntentos(numIntentos) { numIntentos = it }
        Spacer(modifier = Modifier.size(16.dp))
        BotonJugar(navController, isPlayEnable, sliderPosition, numIntentos)
        Spacer(modifier = Modifier.size(16.dp))
        BotonAtras(navController)
    }
}

@Composable
fun Saludo(modifier: Modifier, user: String)
{
    Text(
        text = "Hola, $user",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun Deslizador(sliderPosition: Float, function: (Float) -> Unit)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text( text = "Número al que apuestas:" )

        Slider(
            value = sliderPosition,
            onValueChange = function,
            valueRange = 1f..10f,
            steps = 8,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF4EA8E9),
                activeTrackColor = Color(0xFF4EA8E9),
                inactiveTrackColor = Color(0xFFB5E6FF),
                activeTickColor = Color.Black,
                inactiveTickColor = Color.White
            )
        )

        Text( text = "%.0f".format(sliderPosition) )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumeroIntentos(numIntentos: String, function: (String) -> Unit)
{
    val contexto = LocalContext.current

    TextField(
        value = numIntentos,
        onValueChange = {
            if (it.length <= 2) function(it)
            else Toast.makeText(contexto, "¡No más de 2 caracteres!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedPlaceholderColor = Color(0xFF999999),
            unfocusedPlaceholderColor = Color(0xFF999999),
            focusedTextColor = Color(0xFF020202),
            unfocusedTextColor = Color(0xFF020202),
            containerColor = Color(0xFFF0F0F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword // Así solo se pueden escribir caracteres numéricos
        ),
        placeholder = { Text(text = "Número de intentos") }
    )
}

@Composable
fun BotonJugar(navController: NavController, isPlayEnable: Boolean, numeroApostado: Float, numIntentos: String)
{
    Button(
        onClick = {
            navController.navigate(Routes.Pantalla3.createRoute(numeroApostado.toInt(), numIntentos.toInt()))
        },
        enabled = isPlayEnable,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ){
        Text( text = "Jugar" )
    }
}

@Composable
fun BotonAtras(navController: NavController)
{
    Button(
        onClick = {
            navController.navigate(Routes.Pantalla1.route)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ){
        Text( text = "Volver a la Pantalla de Login" )
    }
}