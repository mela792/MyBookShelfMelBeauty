package com.example.melbeauty.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melbeauty.Servicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun HomeScreen(
        usuario: String,
        serviciosNuevos: List<Servicio>,
        onVolver: () -> Unit,
        onAnadirServicio: () -> Unit
    ) {

    val rosa = Color(0xFFF8BBD0)
    val crema = Color(0xFFFFF8F0)
    val marron = Color(0xFF5D4037)

    var categoria by remember { mutableStateOf("Todos") }

    val servicios = listOf(
        Servicio("Corte de pelo", "Lavado y corte", 15, "Peluquería"),
        Servicio("Peinado", "Peinado para eventos", 20, "Peluquería"),
        Servicio("Manicura", "Cuidado de uñas", 18, "Manicura"),
        Servicio("Uñas de gel", "Aplicación de uñas de gel", 30, "Manicura"),
        Servicio("Maquillaje", "Maquillaje para eventos", 35, "Maquillaje"),
        Servicio("Limpieza facial", "Tratamiento facial", 25, "Tratamientos")
    )

    val todosServicios = servicios + serviciosNuevos

    val serviciosFiltrados = if (categoria == "Todos") {
        todosServicios
    } else {
        todosServicios.filter {
            it.categoria == categoria
        }
    }

    Scaffold(
        containerColor = crema,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MelBeauty",
                        color = marron
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = { onVolver() }
                    ) {
                        Text(
                            text = "←",
                            fontSize = 24.sp,
                            color = marron
                        )
                    }
                },

                actions = {

                    IconButton(
                        onClick = {
                            categoria = "Todos"
                        }
                    ) {
                        Text(
                            text = "⌂",
                            fontSize = 25.sp,
                            color = marron
                        )
                    }

                    IconButton(
                        onClick = {
                            onAnadirServicio()
                        }
                    ) {
                        Text(
                            text = "+",
                            fontSize = 26.sp,
                            color = marron
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = rosa
                )
            )
        },

        bottomBar = {
            NavigationBar(
                containerColor = rosa
            ) {

                NavigationBarItem(
                    selected = categoria == "Todos",
                    onClick = { categoria = "Todos" },
                    icon = { Text("🏠") },
                    label = { Text("Todos") }
                )

                NavigationBarItem(
                    selected = categoria == "Peluquería",
                    onClick = { categoria = "Peluquería" },
                    icon = { Text("✂") },
                    label = { Text("Pelo") }
                )

                NavigationBarItem(
                    selected = categoria == "Manicura",
                    onClick = { categoria = "Manicura" },
                    icon = { Text("💅") },
                    label = { Text("Uñas") }
                )

                NavigationBarItem(
                    selected = categoria == "Maquillaje",
                    onClick = { categoria = "Maquillaje" },
                    icon = { Text("💄") },
                    label = { Text("Maquillaje") }
                )

                NavigationBarItem(
                    selected = categoria == "Tratamientos",
                    onClick = { categoria = "Tratamientos" },
                    icon = { Text("✨") },
                    label = { Text("Facial") }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAnadirServicio()
                },
                containerColor = rosa
            ) {
                Text(
                    text = "+",
                    fontSize = 28.sp,
                    color = marron
                )
            }
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "¡Bienvenida, $usuario!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = marron
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Tu espacio de belleza",
                fontSize = 16.sp,
                color = marron
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Nuestros servicios",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = marron
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {

                items(serviciosFiltrados) { servicio ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFCE4EC)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = servicio.nombre,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = marron
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = servicio.descripcion,
                                fontSize = 14.sp,
                                color = marron
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "${servicio.precio} €",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = marron
                            )
                        }
                    }
                }
            }
        }
    }
}
