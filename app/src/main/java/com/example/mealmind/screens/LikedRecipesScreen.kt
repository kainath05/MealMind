package com.example.mealmind.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.data.RecipeViewModel
import com.example.mealmind.data.RecipeViewModelFactory
import com.example.mealmind.data.database.AppDatabase
import com.example.mealmind.data.database.Recipe
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext

@Composable
fun LikedRecipesScreen(userId: Int) {
    val recipeViewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(AppDatabase.getDatabase(LocalContext.current).recipeDao()))

    // Managing state for liked recipes
    val likedRecipes = remember { mutableStateOf<List<Recipe>>(emptyList()) }

    // Fetch liked recipes for the current user
    LaunchedEffect(userId) {
        recipeViewModel.getRecipesByUserId(userId) { recipes ->
            likedRecipes.value = recipes
        }
    }

    LikedRecipesContent(likedRecipes = likedRecipes.value)
}

@Composable
fun LikedRecipesContent(likedRecipes: List<Recipe>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(55.dp))
        }
        item {
            Text(
                text = "Liked Recipes:",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        items(likedRecipes) { recipe ->
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ingredients: ${recipe.ingredients}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Instructions: ${recipe.instructions}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
