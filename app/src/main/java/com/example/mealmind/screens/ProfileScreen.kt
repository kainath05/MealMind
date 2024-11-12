package com.example.mealmind.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
//        Text(
//            text = "Profile",
//            style = MaterialTheme.typography.displayMedium,
//            color = MaterialTheme.colorScheme.secondary
//        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}) {
            Text("Recipes", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}) {
            Text("Preference Form", style = MaterialTheme.typography.bodyLarge)
        }
    }

}

