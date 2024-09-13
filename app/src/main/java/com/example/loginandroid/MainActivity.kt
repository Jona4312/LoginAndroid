package com.example.loginandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.loginandroid.ui.theme.LoginAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username")
        setContent {
            LoginAndroidTheme {
                WelcomeScreen(username)
            }
        }
    }
}

class WelcomeScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username")
        setContent {
            LoginAndroidTheme {
                WelcomeScreen()
            }
        }
    }
}


@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (username.isEmpty()) {
                    Text("Username", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                if (password.isEmpty()) {
                    Text("Password", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(context, WelcomeActivity::class.java).apply {
                        putExtra("username", username)
                    }
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Welcome, ${intent.getStringExtra("username")}")
        }
    }
}
@Composable
fun WelcomeScreen(username: String?) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var comuna by remember { mutableStateOf("") }
    var observacion by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome, $username!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (nombre.isEmpty()) {
                    Text("Nombre", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = apellido,
            onValueChange = { apellido = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (apellido.isEmpty()) {
                    Text("Apellido", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = comuna,
            onValueChange = { comuna = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (comuna.isEmpty()) {
                    Text("Comuna", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = observacion,
            onValueChange = { observacion = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (observacion.isEmpty()) {
                    Text("Observación", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombre.isEmpty() || apellido.isEmpty() || comuna.isEmpty() || observacion.isEmpty()) {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val emailIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "User Information")
                        putExtra(Intent.EXTRA_TEXT, "Nombre: $nombre\nApellido: $apellido\nComuna: $comuna\nObservación: $observacion")
                    }
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Datos")
        }
    }
}