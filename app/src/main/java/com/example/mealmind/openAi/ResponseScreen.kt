package com.example.mealmind.openAi


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.openAi.OpenAiViewModel


@Composable
fun ResponseScreen(
    modifier: Modifier = Modifier, // dont really use this for now
    viewModel: OpenAiViewModel = viewModel())
{
    var ingredients by remember { mutableStateOf("") }
    var cuisine by remember { mutableStateOf("") }
    var mealType by remember { mutableStateOf("") }
    var dietaryRestrictions by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // parameter for ingredients
        TextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Enter ingredients") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // parameter for cuisine type
        TextField(
            value = cuisine,
            onValueChange = { cuisine = it },
            label = { Text("Enter cuisine type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // triggers GET request to endpoint
        Button(
            onClick = { viewModel.getResponse(cuisine, mealType, dietaryRestrictions) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Recipe")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // display query output
        RecipeComposable(responseText = viewModel.responseText.value)
    }
}


