package com.example.mealmind.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onDarkThemeToggle: () -> Unit,
    onNavigateToPreferences: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Button(onClick = onDarkThemeToggle) {
            Text(text = "Dark Theme")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToPreferences) {
            Text(text = "Go to Preferences")
        }
    }
}
