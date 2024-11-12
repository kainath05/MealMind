//package com.example.mealmind.screens
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.selection.selectable
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.semantics.Role
//import androidx.compose.ui.unit.dp
//
//// 1. Stateless version of FormScreen that uses LazyColumn
//@Composable
//fun FormScreen(
//    modifier: Modifier = Modifier,
//    title: String,
//    options: List<String>,
//    selectedOption: String,
//    onOptionSelected: (String) -> Unit
//) {
//    LazyColumn(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalAlignment = Alignment.Start
//    ) {
//        item {
//            // Title for the section
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.secondary,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//        }
//
//        // Display radio buttons for each option using items
//        items(options) { option ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .selectable(
//                        selected = (option == selectedOption),
//                        onClick = { onOptionSelected(option) },
//                        role = Role.RadioButton
//                    )
//                    .padding(vertical = 4.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                RadioButton(
//                    selected = (option == selectedOption),
//                    onClick = null // Remove redundant onClick from RadioButton
//                )
//                Text(
//                    text = option,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//            }
//        }
//    }
//}
//
//
//// 2. Stateful wrapper that holds the selected options for each category
//@Composable
//fun StatefulFormScreen(modifier: Modifier = Modifier) {
//    // State for selected options in each category
//    val dietaryOptions = listOf("Vegan", "Vegetarian", "Dairy-Free", "Gluten-Free")
//    var selectedDietaryOption by remember { mutableStateOf(dietaryOptions[0]) }
//
//    val mealTypeOptions = listOf("Breakfast", "Lunch", "Dinner", "Snack")
//    var selectedMealType by remember { mutableStateOf(mealTypeOptions[0]) }
//
//    val spiceLevelOptions = listOf("Mild", "Medium", "Spicy", "Extra Spicy")
//    var selectedSpiceLevel by remember { mutableStateOf(spiceLevelOptions[0]) }
//
//    // Ensure options are not null, defaulting to an empty list if null
//    val safeDietaryOptions = dietaryOptions ?: emptyList()
//    val safeMealTypeOptions = mealTypeOptions ?: emptyList()
//    val safeSpiceLevelOptions = spiceLevelOptions ?: emptyList()
//
//    // Use LazyColumn to render each form section
//    LazyColumn(
//        modifier = modifier.padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(24.dp)
//    ) {
//        item {
//            // Dietary options section
//            FormScreen(
//                title = "Dietary Restrictions",
//                options = safeDietaryOptions,
//                selectedOption = selectedDietaryOption,
//                onOptionSelected = { selectedDietaryOption = it }
//            )
//        }
//
//        item {
//            // Meal type section
//            FormScreen(
//                title = "Meal Type",
//                options = safeMealTypeOptions,
//                selectedOption = selectedMealType,
//                onOptionSelected = { selectedMealType = it }
//            )
//        }
//
//        item {
//            // Spice level section
//            FormScreen(
//                title = "Spice Level",
//                options = safeSpiceLevelOptions,
//                selectedOption = selectedSpiceLevel,
//                onOptionSelected = { selectedSpiceLevel = it }
//            )
//        }
//    }
//}
