package com.example.mealmind.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.data.PreferenceViewModel
import com.example.mealmind.data.PreferenceViewModelFactory
import com.example.mealmind.data.database.AppDatabase

@Composable
fun RecipesScreen(modifier: Modifier, onDetails: () -> Unit, preferenceViewModel: PreferenceViewModel = viewModel(factory = PreferenceViewModelFactory(
    AppDatabase.getDatabase(LocalContext.current).preferenceDao()))) {

    //TO CHECK IF PREFERENCES POPULATED DATABASE!!
//    var showDialog by remember { mutableStateOf(false) }
//    var dialogMessage by remember { mutableStateOf("") }
//
//        LaunchedEffect(key1 = Unit) { //this checks if theres users in the database, has to be a coroutine since its a suspended function
//            val preferencesExist = preferenceViewModel.hasPreferences()
//            dialogMessage = if (preferencesExist) {
//                "There are preferences in the database."
//            } else {
//                "No preferences in the database."
//            }
//            showDialog = true
//        }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            confirmButton = {
//                Button(onClick = { showDialog = false }) {
//                    Text("OK")
//                }
//            },
//            title = { Text(text = "Is Preference Table Populated!") },
//            text = { Text(text = dialogMessage) }
//        )
//    }

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