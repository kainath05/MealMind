package com.example.mealmind.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealmind.R
import com.example.mealmind.data.UserViewModel
import com.example.mealmind.data.UserViewModelFactory 
import com.example.mealmind.data.database.User
import com.example.mealmind.data.database.AppDatabase

@Composable
fun RegisterScreenStateful(modifier: Modifier, onRegisterSuccess: () -> Unit, userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(AppDatabase.getDatabase(LocalContext.current).userDao()))) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

//    LaunchedEffect(key1 = Unit) { //this checks if theres users in the database, has to be a coroutine since its a suspended function
//        val userExists = userViewModel.hasUsers()
//        dialogMessage = if (userExists) {
//            "There are users in the database."
//        } else {
//            "No users in the database."
//        }
//        showDialog = true
//    }

    //alert dialog for registration status
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text(text = "Registration Status") },
            text = { Text(text = dialogMessage) }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "Pizza Background",
            modifier = Modifier.fillMaxSize().graphicsLayer(alpha = 0.5f), //opacity
            contentScale = ContentScale.FillBounds,
        )

        RegisterScreenStateless(
            email = emailState.value,
            onEmailChange = { emailState.value = it },
            password = passwordState.value,
            onPasswordChange = { passwordState.value = it },
            confirmPassword = confirmPasswordState.value,
            onConfirmPasswordChange = { confirmPasswordState.value = it },
            onRegisterClick = {
                if (passwordState.value == confirmPasswordState.value) {
                    val user = User(email = emailState.value, password = passwordState.value,
                        profileImage = 0
                    )
                    userViewModel.insert(user)
                    onRegisterSuccess()
                } else {
                    dialogMessage = "Passwords do not match"
                    showDialog = true
                }
            },
            isButtonEnabled = emailState.value.isNotEmpty() && //used to disable button until all fields are filled
                    passwordState.value.isNotEmpty() &&
                    confirmPasswordState.value.isNotEmpty()
        )
    }
}

@Composable
fun RegisterScreenStateless(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    isButtonEnabled: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email", style = MaterialTheme.typography.bodyLarge) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor =  MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password", style = MaterialTheme.typography.bodyLarge) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor =  MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("Confirm Password", style = MaterialTheme.typography.bodyLarge) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor =  MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth().size(60.dp),
            enabled = isButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Get Started", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
