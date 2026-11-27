package com.example.melbeauty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.melbeauty.navigation.NavigationWrapper
import com.example.melbeauty.ui.theme.MelBeautyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MelBeautyTheme {
                NavigationWrapper()
            }
        }
    }
}
