package com.example.mealmind.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.data.PreferenceViewModel
import com.example.mealmind.data.PreferenceViewModelFactory
import com.example.mealmind.data.database.AppDatabase
import com.example.mealmind.data.database.Preference
import com.example.mealmind.openAi.OpenAiViewModel

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    userId: Int,
    onNavigateToDetails: (String) -> Unit,
    preferenceViewModel: PreferenceViewModel = viewModel(factory = PreferenceViewModelFactory(
        AppDatabase.getDatabase(LocalContext.current).preferenceDao()
    )),
    openAiViewModel: OpenAiViewModel = viewModel()
) {
    val preferences = remember { mutableStateOf<List<Preference>>(emptyList()) }
    val responses = openAiViewModel.responses

    LaunchedEffect(userId) {
        preferenceViewModel.getPreferencesByUserId(userId) { prefList ->
            preferences.value = prefList
            if (prefList.isNotEmpty()) {
                val preference = prefList.first()
                openAiViewModel.getResponse(
                    cuisine = preference.cuisineType,
                    dietaryRestrictions = preference.dietaryOptions,
                    mealType = preference.mealOptions
                )
            }
        }
    }

    RecipesScreenStateless(
        modifier = modifier,
        recipes = responses.value,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
fun RecipesScreenStateless(
    modifier: Modifier = Modifier,
    recipes: List<String>,
    onNavigateToDetails: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Generated Recipes:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (recipes.isNotEmpty()) {
            recipes.forEach { recipe ->
                Button(
                    onClick = { onNavigateToDetails(recipe) },
                    modifier = Modifier
                        .padding(vertical = 13.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = recipe, color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyLarge)
                }
            }
        } else {
            Text(text = "Loading recipes...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
