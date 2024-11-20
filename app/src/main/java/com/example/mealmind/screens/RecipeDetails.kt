package com.example.mealmind.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.openAi.OpenAiViewModel

@Composable
fun RecipeDetailsScreen(
    recipeName: String,
    openAiViewModel: OpenAiViewModel = viewModel()
) {
    val ingredientResponse = openAiViewModel.responseText

    LaunchedEffect(recipeName) {
        if (recipeName.isNotEmpty()) {
            openAiViewModel.getRecipe(recipeName)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Ingredients for $recipeName:", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        if (ingredientResponse.value.isNotEmpty()) {
            Text(text = ingredientResponse.value, style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(text = "Loading ingredients...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

