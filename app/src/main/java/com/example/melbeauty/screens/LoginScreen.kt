package com.example.melbeauty.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melbeauty.R

@Composable
fun LoginScreen(onLogin: (String) -> Unit) {

    // Colores de MelBeauty
    val rosa = Color(0xFFF8BBD0)
    val crema = Color(0xFFFFF8F0)
    val marron = Color(0xFF5D4037)

    // Contexto para Toast y cerrar la aplicación
    val context = LocalContext.current

    // Estados
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarContrasena by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(crema)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "MelBeauty",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = marron
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Tu espacio de belleza",
                fontSize = 16.sp,
                color = marron
            )

            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation =
                    if (mostrarContrasena)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(
                        onClick = {
                            mostrarContrasena = !mostrarContrasena
                        }
                    ) {
                        Text(
                            text = if (mostrarContrasena)
                                "Ocultar"
                            else
                                "Ver",
                            color = marron
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    onLogin(usuario)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = rosa
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión", color = marron)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = rosa
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse", color = marron)
            }

            Spacer(modifier = Modifier.height(25.dp))

            HorizontalDivider(color = rosa)

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "O inicia sesión con",
                fontSize = 14.sp,
                color = marron
            )

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "No implementado",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rosa
                    )
                ) {
                    Text("Google", color = marron)
                }

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "No implementado",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rosa
                    )
                ) {
                    Text("Facebook", color = marron)
                }
            }
        }

        Text(
            text = "MelBeauty | Versión 1.0 | Melanie",
            fontSize = 12.sp,
            color = marron,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 65.dp)
        )

        Image(
            painter = painterResource(
                id = R.drawable.logo_melbeauty
            ),
            contentDescription = "Logo MB",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 85.dp, start = 15.dp)
                .size(70.dp)
        )

        FloatingActionButton(
            onClick = {
                (context as? Activity)?.finish()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            containerColor = rosa
        ) {
            Text(
                text = "X",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = marron
            )
        }
    }
}
