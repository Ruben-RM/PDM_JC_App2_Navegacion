package com.example.pdm_jc_app2_navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
        SSBody(Modifier.align(Alignment.Center), user)
    }
}

@Composable
fun SSBody (modifier: Modifier, user: String)
{
    Text(
        text = "Hola, $user",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}