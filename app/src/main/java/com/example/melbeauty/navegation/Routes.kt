package com.example.melbeauty.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

@Serializable
data class Home(val usuario: String) : NavKey

@Serializable
data object AnadirServicio : NavKey