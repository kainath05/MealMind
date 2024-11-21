package com.example.mealmind.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mealmind.R

@Composable
fun HomeScreen(modifier: Modifier,  onGetStartedClick: () -> Unit, onLoginClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.mealmindlogo),
                contentDescription = "MealMind Logo",
                modifier = Modifier
                    .width(200.dp)  //to make picture smaller
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "App that recommends smart recipes and meal plans based on YOUR PREFERENCES",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Made by Kainath, Kyle, Zach",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.eyes),
                contentDescription = "MealMind Logo",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Start Your MealMind Journey Now", style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onGetStartedClick, modifier = Modifier.height(50.dp).width(200.dp)
            ) {
                Text("Get Started", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Already have an account?"
            )

            Spacer(modifier = Modifier.height(13.dp))

            Button(onClick = onLoginClick, modifier = Modifier.height(50.dp).width(200.dp)) {
                Text("Login", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
