package com.example.pdm_jc_app2_navegacion

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun FirstScreen()
{
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
    {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier)
{
    val activity = LocalContext.current as? Activity

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar App",
        modifier = modifier.clickable { activity?.finish() }
    )
}

@Composable
fun Body(modifier: Modifier) {
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
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        GuestButton()
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
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
            .clickable { checkForgotPassword() }
    )
}

@Composable
fun LoginButton(isLoginEnable: Boolean)
{
    Button(
        onClick = { checkLogin() },
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
fun GuestButton()
{
    Button(
        onClick = { checkLogin() },
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

@Composable
fun Footer(modifier: Modifier)
{
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9F9F9F))
                .height(1.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Don't have an account? ",
                fontSize = 12.sp,
                color = Color(0xFFB5B5B5)
            )
            Text(
                text = "Sign up",
                fontSize = 12.sp,
                color = Color(0xFF4EA8E9),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { createAccount() }
            )
        }
    }
}

fun createAccount() {
    TODO("Not yet implemented")
}

fun checkLogin() {
    //TODO("Not yet implemented")
}


fun checkForgotPassword() {
    TODO("Not yet implemented")
}