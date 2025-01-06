package com.example.pdm_jc_app2_navegacion

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController)
{
    Box(Modifier
        .fillMaxSize()
        .padding(8.dp)
    )
    {
        FSHeader(Modifier.align(Alignment.TopEnd))
        FSBody(Modifier.align(Alignment.Center), navController)
    }
}

@Composable
fun FSHeader(modifier: Modifier)
{
    val activity = LocalContext.current as? Activity

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar App",
        modifier = modifier.clickable { activity?.finish() }
    )
}

@Composable
fun FSBody(modifier: Modifier, navController: NavController) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }

    isLoginEnable = (user != "") && (password != "")

    Column(
        modifier = modifier
    ) {
        Titulo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        User(user) { user = it }
        Spacer(modifier = Modifier.size(8.dp))
        Password(password) { password = it }
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(user, password, isLoginEnable, navController)
        Spacer(modifier = Modifier.size(16.dp))
        GuestButton(navController)
    }
}

@Composable
fun Titulo(modifier: Modifier)
{
    Text(
        text = "JetPack Compose App2",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(user: String, function: (String) -> Unit)
{
    TextField(
        value = user,
        onValueChange = { function(it) },
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
            keyboardType = KeyboardType.Text
        ),
        placeholder = { Text(text = "Usuario") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, function: (String) -> Unit)
{
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { function(it) },
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
            keyboardType = KeyboardType.Password
        ),
        placeholder = { Text(text = "Contraseña") },
        trailingIcon = {
            val image =
                if(passwordVisibility)
                    Icons.Filled.VisibilityOff
                else
                    Icons.Filled.Visibility

            IconButton(
                onClick = { passwordVisibility = !passwordVisibility }
            ){
                Icon(
                    imageVector = image,
                    contentDescription = "Mostrar Contraseña"
                )
            }
        },
        visualTransformation =
        if(passwordVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}

@Composable
fun LoginButton(user: String, password: String, isLoginEnable: Boolean, navController: NavController)
{
    val openAlertDialog = remember{ mutableStateOf(false) }

    when {
        openAlertDialog.value == true ->
            DialogErrorLogin(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = { openAlertDialog.value = false }
            )
    }

    Button(
        onClick = {
            openAlertDialog.value = checkLogin(user, password)

            if(openAlertDialog.value == false)
                navController.navigate(Routes.Pantalla2.createRoute(user))
        },
        enabled = isLoginEnable,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ){
        Text( text = "Iniciar Sesión")
    }
}

@Composable
fun DialogErrorLogin(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
){
    AlertDialog(
        title = {
            Text(text = "Error en Login")
        },
        text = {
            Text(text = "El usuario o la contraseña son incorrectos.")
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation
            ) { Text("De acuerdo.") }
        }
    )
}

@Composable
fun GuestButton(navController: NavController)
{
    Button(
        onClick = {
            navController.navigate(Routes.Pantalla2.createRoute("Invitado"))
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
        Text( text = "Iniciar Sesión como Invitado")
    }
}

fun checkLogin(user: String, password: String): Boolean
{
    val usersMap = mapOf<String, String>("user1" to "pw1", "user2" to "pw2", "user2" to "pw3")
    var openAlertDialog = true

    if(usersMap.containsKey(user))
        if(usersMap[user] == password)
            openAlertDialog = false

    return openAlertDialog
}