package com.example.mealmind.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mealmind.R

@Composable
fun RecipesScreen(modifier: Modifier, onDetails: () -> Unit) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("RECIPESSS") //connects to database and needs a liking feature
        IconButton(
            onClick = onDetails
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Back Button"
            )
        }
    }
}