package com.example.mealmind.screens

import android.app.AlertDialog
import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.data.PreferenceViewModel
import com.example.mealmind.data.PreferenceViewModelFactory
import com.example.mealmind.data.database.AppDatabase
import androidx.compose.material3.*
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Generated Recipes:", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (responses.value.isNotEmpty()) {
            responses.value.forEach { recipe ->
                Button(
                    onClick = { onNavigateToDetails(recipe) },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = recipe)
                }
            }
        } else {
            Text(text = "Loading recipes...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}






