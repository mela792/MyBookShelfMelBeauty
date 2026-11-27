package com.example.melbeauty.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melbeauty.Servicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnadirServicioScreen(
    onVolver: () -> Unit,
    onGuardar: (Servicio) -> Unit
) {

    val rosa = Color(0xFFF8BBD0)
    val crema = Color(0xFFFFF8F0)
    val marron = Color(0xFF5D4037)

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    var expandido by remember { mutableStateOf(false) }

    var error by remember { mutableStateOf(false) }

    val categorias = listOf(
        "Peluquería",
        "Manicura",
        "Maquillaje",
        "Tratamientos"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(crema)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Añadir servicio",
            fontSize = 26.sp,
            color = marron
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del servicio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio (€)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        ExposedDropdownMenuBox(
            expanded = expandido,
            onExpandedChange = {
                expandido = !expandido
            }
        ) {

            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandido
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandido,
                onDismissRequest = {
                    expandido = false
                }
            ) {

                categorias.forEach { opcion ->

                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            categoria = opcion
                            expandido = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (error) {
            Text(
                text = "Completa todos los campos y escribe un precio válido",
                color = Color.Red,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(15.dp))
        }

        Button(
            onClick = {

                val precioNumero = precio.toIntOrNull()

                if (
                    nombre.isNotBlank() &&
                    descripcion.isNotBlank() &&
                    precioNumero != null &&
                    precioNumero > 0 &&
                    categoria.isNotBlank()
                ) {

                    val nuevoServicio = Servicio(
                        nombre = nombre.trim(),
                        descripcion = descripcion.trim(),
                        precio = precioNumero,
                        categoria = categoria
                    )

                    onGuardar(nuevoServicio)

                } else {
                    error = true
                }
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = rosa
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar", color = marron)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onVolver()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = rosa
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar", color = marron)
        }
    }
}
