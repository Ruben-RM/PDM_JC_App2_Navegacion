package com.example.pdm_jc_app2_navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pdm_jc_app2_navegacion.ui.theme.PDM_JC_App2_NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDM_JC_App2_NavegacionTheme {
                Main()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Main()
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        val navigationController = rememberNavController()

        NavHost(
            navController = navigationController,
            startDestination = Routes.Pantalla1.route
        ){
            composable(Routes.Pantalla1.route) { FirstScreen(navigationController) }
            composable(
                Routes.Pantalla2.route,
                arguments = listOf(navArgument("user") { type = NavType.StringType })
            ) {
                SecondScreen(navigationController, it.arguments?.getString("user").orEmpty())
            }
        }
    }
}