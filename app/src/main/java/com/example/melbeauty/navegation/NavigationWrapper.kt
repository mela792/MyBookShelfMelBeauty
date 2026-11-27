package com.example.melbeauty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.melbeauty.Servicio
import com.example.melbeauty.screens.LoginScreen
import com.example.melbeauty.screens.HomeScreen
import com.example.melbeauty.screens.AnadirServicioScreen

@Composable
fun NavigationWrapper() {

    val backStack = rememberNavBackStack(Login)

    val nuevosServicios = remember {
        mutableStateListOf<Servicio>()
    }

    NavDisplay(
        backStack = backStack,

        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },

        entryProvider = entryProvider {

            entry<Login> {
                LoginScreen { usuario ->
                    backStack.add(Home(usuario))
                }
            }

            entry<Home> { pantalla ->
                HomeScreen(
                    usuario = pantalla.usuario,
                    serviciosNuevos = nuevosServicios,

                    onVolver = {
                        backStack.removeAt(backStack.lastIndex)
                    },

                    onAnadirServicio = {
                        backStack.add(AnadirServicio)
                    }
                )
            }
            entry<AnadirServicio> {
                AnadirServicioScreen(
                    onVolver = {
                        backStack.removeAt(backStack.lastIndex)
                    },

                    onGuardar = { servicio ->
                        nuevosServicios.add(servicio)
                        backStack.removeAt(backStack.lastIndex)
                    }
                )
            }
        }
    )
}
