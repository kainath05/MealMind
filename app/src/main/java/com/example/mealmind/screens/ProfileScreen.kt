package com.example.mealmind.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mealmind.R

@Composable
fun ProfileScreen(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.default_avatar_profile_icon),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .width(300.dp)  //to make picture smaller
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Email placeholder"
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { }, modifier = Modifier.height(50.dp).width(200.dp)
            ) {
                Text("Recipes", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(13.dp))

            Button(onClick = {}, modifier = Modifier.height(50.dp).width(200.dp)) {
                Text("Preference Form", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

}

