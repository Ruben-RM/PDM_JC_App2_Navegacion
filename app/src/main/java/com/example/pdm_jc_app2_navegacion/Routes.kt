package com.example.pdm_jc_app2_navegacion

sealed class Routes(val route: String)
{
    object Pantalla1:Routes("FirstScreen")
    object Pantalla2:Routes("SecondScreen/{user}")
    { fun createRoute(user: String) = "SecondScreen/$user" }
}