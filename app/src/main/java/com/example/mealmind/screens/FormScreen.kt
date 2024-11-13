package com.example.mealmind.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = null // Disable RadioButton's own click handler
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun StatefulFormScreen(modifier: Modifier = Modifier, onSubmit: () -> Unit) {
    val dietaryOptions = listOf("Vegan", "Vegetarian", "Dairy-Free", "Gluten-Free", "None")
    var selectedDietaryOption by remember { mutableStateOf(dietaryOptions[0]) }

    val mealTypeOptions = listOf("Breakfast", "Lunch", "Dinner", "Snack", "Dessert")
    var selectedMealType by remember { mutableStateOf(mealTypeOptions[0]) }

    val cuisineTypeOptions = listOf(
        "Italian", "Mexican", "Chinese", "Indian", "Thai", "Japanese", "French", "Mediterranean",
        "Greek", "Spanish", "Lebanese", "American", "African", "Korean", "Vietnamese", "Moroccan",
        "Russian", "British", "German", "Canadian"
    )
    var selectedCuisineTypeOptions by remember { mutableStateOf(cuisineTypeOptions[0]) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Preference Form",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                FormScreen(
                    title = "Dietary Restrictions",
                    options = dietaryOptions,
                    selectedOption = selectedDietaryOption,
                    onOptionSelected = { selectedDietaryOption = it }
                )
            }

            item {
                FormScreen(
                    title = "Meal Type",
                    options = mealTypeOptions,
                    selectedOption = selectedMealType,
                    onOptionSelected = { selectedMealType = it }
                )
            }

            item {
                FormScreen(
                    title = "Cuisine Type",
                    options = cuisineTypeOptions,
                    selectedOption = selectedCuisineTypeOptions,
                    onOptionSelected = { selectedCuisineTypeOptions = it }
                )
            }
        }

        Button(
            onClick = onSubmit, //go to recipes screen
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp).size(45.dp)
        ) {
            Text("Submit", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
