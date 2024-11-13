package com.example.mealmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.mealmind.components.ScaffoldTopBar
import com.example.mealmind.data.database.AppDatabase
import com.example.mealmind.screens.RegisterScreenStateful
import com.example.mealmind.ui.theme.MealMindTheme
import com.example.mealmind.openAi.OpenAiViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MealMindTheme {
                Scaffold(
                    topBar = { ScaffoldTopBar()},
                    content = { paddingValues ->
                        RegisterScreenStateful(modifier = Modifier.padding(paddingValues))
                    }
                )

                }
            }
        }
    }
