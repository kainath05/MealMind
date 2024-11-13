package com.example.mealmind.openAi

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RecipeComposable(responseText: String) {
    Text(
        text = responseText,
        modifier = Modifier.padding(16.dp)
    )
}