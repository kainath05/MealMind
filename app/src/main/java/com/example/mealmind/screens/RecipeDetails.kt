package com.example.mealmind.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color


@Composable
fun RecipeDetailsScreen(
    recipeName: String,
    openAiViewModel: OpenAiViewModel = viewModel()
) {
    val ingredientResponse = openAiViewModel.responseText

    // Fetch recipe details when the recipeName changes
    LaunchedEffect(recipeName) {
        if (recipeName.isNotEmpty()) {
            openAiViewModel.getRecipe(recipeName)
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (ingredients, instructions) = splittingIngredientsAndInstructions(ingredientResponse.value)

        // Recipe Title and Heart Icon Row
        item {
            Text(
                text = "Recipe: $recipeName",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp) // Add some spacing for clarity
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), // Add padding inside the row
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically


            ) {
                Text(
                    text = recipeName,
                    style = MaterialTheme.typography.titleLarge
                )

                HeartIconButton()


            }
        }


        // Ingredients Section
        if (ingredientResponse.value.isNotEmpty()) {
            // Ingredients Header
            item {
                Text(
                    text = "Ingredients:",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Dynamic List of Ingredients
            items(ingredients) { ingredient ->
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Instructions Section
            item {
                Text(
                    text = "Instructions:",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Dynamic List of Instructions
            items(instructions) { instruction ->
                Text(
                    text = instruction,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            // Loading State
            item {
                Text(
                    text = "Loading recipe details...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}






fun splittingIngredientsAndInstructions(recipeDetails: String): Pair<List<String>, List<String>> {
    println("Processing Recipe Details: $recipeDetails") // Debugging

    // Find "Ingredients:" and "Instructions:" headers
    val ingredientsStart = recipeDetails.indexOf("Ingredients:", ignoreCase = true)
    val instructionsStart = recipeDetails.indexOf("Instructions:", ignoreCase = true)

    // Extract ingredients
    val ingredients = if (ingredientsStart != -1) {
        val ingredientsEnd = if (instructionsStart != -1) instructionsStart else recipeDetails.length
        recipeDetails.substring(ingredientsStart + "Ingredients:".length, ingredientsEnd)
            .trim()
            .split("\n")
            .filter { it.isNotBlank() }
            .map { it.trim() }
    } else {
        emptyList()
    }

    // Extract instructions
    val instructions = if (instructionsStart != -1) {
        recipeDetails.substring(instructionsStart + "Instructions:".length)
            .trim()
            .split("\n")
            .filter { it.isNotBlank() }
            .map { it.trim() }
    } else {
        emptyList()
    }

    println("Extracted Ingredients: $ingredients") // Debugging
    println("Extracted Instructions: $instructions") // Debugging

    return Pair(ingredients, instructions)
}


@Composable
fun HeartIconButton(
) {
    // Remember the state of the heart (liked or not)
    IconButton(
        onClick = {}
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.icons8_heart_48
            ),
            contentDescription = "Heart Icon",
            modifier = Modifier.size(39.dp)

        )
    }
}











