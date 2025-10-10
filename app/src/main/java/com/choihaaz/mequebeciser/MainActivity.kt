package com.choihaaz.mequebeciser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.choihaaz.mequebeciser.ui.theme.MeQuebeciserTheme
import com.choihaaz.mequebeciser.ui.translation.TranslationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeQuebeciserTheme {
                TranslationScreen()
            }
        }
    }
}
