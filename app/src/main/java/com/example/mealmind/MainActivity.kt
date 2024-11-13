package com.example.mealmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mealmind.components.ScaffoldTopBar
import com.example.mealmind.navigation.NavigationHost
import com.example.mealmind.screens.HomeScreen
import com.example.mealmind.screens.LoginScreenStateful
import com.example.mealmind.screens.ProfileScreen
import com.example.mealmind.screens.RegisterScreenStateful
import com.example.mealmind.screens.StatefulFormScreen
import com.example.mealmind.ui.theme.MealMindTheme
import com.example.mealmind.openAi.OpenAiViewModel
import com.example.mealmind.openAi.ResponseScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MealMindTheme {
                Scaffold(
                    topBar = { ScaffoldTopBar()},
                    content = { paddingValues ->
                        NavigationHost(modifier = Modifier.padding(paddingValues))
                    }
                )

                }
            }
        }
    }


