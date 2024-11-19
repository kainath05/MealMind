package com.example.mealmind.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.data.UserViewModel
import com.example.mealmind.data.UserViewModelFactory
import com.example.mealmind.data.database.AppDatabase

@Composable
fun ProfileScreen(
    modifier: Modifier,
    onPreference: () -> Unit,
    onRecipe: () -> Unit,
    userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(
        AppDatabase.getDatabase(LocalContext.current).userDao())
    )
) {
    var selectedProfileImage by remember { mutableStateOf<Int?>(null) }
    var profileImageResource by remember { mutableStateOf(R.drawable.default_avatar_profile_icon) }
    var email by remember { mutableStateOf("") }

//    LaunchedEffect(Unit) {
//        val user = userViewModel.getUserByEmail(email)
//        user?.let {
//            email = it.email
//            profileImageResource = it.profileImage
//        }
//    }

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

            // Profile image selection (3 options)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val images = listOf(R.drawable.bear, R.drawable.walrus, R.drawable.dog)
                images.forEach { imageId ->
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Profile Icon",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clickable {
                                selectedProfileImage = imageId
                                profileImageResource = imageId
                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            selectedProfileImage?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Selected Profile Image",
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(16.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Email: $email") //doesnt show yet

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onRecipe, modifier = Modifier.height(50.dp).width(200.dp)
            ) {
                Text("Recipes", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(13.dp))

            Button(onClick = onPreference, modifier = Modifier.height(50.dp).width(200.dp)) {
                Text("Preference Form", style = MaterialTheme.typography.bodyLarge)
            }
        }

//        LaunchedEffect(selectedProfileImage) {
//            selectedProfileImage?.let { selectedImage ->
//                userViewModel.updateProfileImage(email, selectedImage)
//            }
//        }
    }
}
