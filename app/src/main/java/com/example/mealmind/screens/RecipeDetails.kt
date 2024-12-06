package com.example.mealmind.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.data.RecipeViewModel
import com.example.mealmind.data.RecipeViewModelFactory
import com.example.mealmind.data.SharedViewModel
import com.example.mealmind.data.database.AppDatabase
import com.example.mealmind.data.database.Recipe
import com.example.mealmind.openAi.OpenAiViewModel
import com.example.mealmind.openAi.OpenAiViewModelFactory

@Composable
fun RecipeDetailsScreen(
    recipeName: String,
    openAiViewModel: OpenAiViewModel = viewModel(factory = OpenAiViewModelFactory(LocalContext.current)),
    sharedViewModel: SharedViewModel
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

        item {
            Spacer(modifier = Modifier.height(55.dp))
        }
        item {
            Text(
                text = recipeName,
                style = MaterialTheme.typography.labelSmall
            )
        }
        item {
            HeartIconButton(
                recipeName = recipeName,
                ingredients = ingredients.joinToString("\n"),
                instructions = instructions.joinToString("\n"),
                userId = sharedViewModel.userId
            )
        }

        // Ingredients Section
        if (ingredientResponse.value.isNotEmpty()) {
            item {
                Text(
                    text = "Ingredients:",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
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
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            items(instructions) { instruction ->
                Text(
                    text = instruction,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
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

    return Pair(ingredients, instructions)
}

@Composable
fun HeartIconButton(
    recipeName: String,
    ingredients: String,
    instructions: String,
    userId: Int,
    recipeViewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(
        AppDatabase.getDatabase(LocalContext.current).recipeDao()
    ))
) {
    val isLiked = remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            if (!isLiked.value) { // Ensure the button works only if it hasn't been clicked before
                isLiked.value = true
                // Insert recipe into the database
                recipeViewModel.insert(
                    Recipe(
                        userId = userId,
                        title = recipeName,
                        ingredients = ingredients,
                        instructions = instructions
                    )
                )
            }
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icons8_heart_48),
            contentDescription = "Heart Icon",
            tint = if (isLiked.value) Color.Red else Color.Gray,
            modifier = Modifier.size(30.dp)
        )
    }
}

