package com.example.mealmind.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onDarkThemeToggle: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(56.dp))


        Button(
            onClick = onDarkThemeToggle,
            modifier = Modifier.size(width = 200.dp, height = 60.dp) // Set button size
        ) {
            Text(text = "Dark Theme", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.size(width = 200.dp, height = 60.dp) // Set button size
        ) {
            Text(text = "Go to Profile", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
