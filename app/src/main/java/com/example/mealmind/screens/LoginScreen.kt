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
import com.example.mealmind.data.database.AppDatabase

@Composable
fun LoginScreenStateful(modifier: Modifier, onLoginSuccess: (String, Int) -> Unit, userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(
    AppDatabase.getDatabase(LocalContext.current).userDao()))) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    fun handleLogin(email: String, password: String) { //checks for bad passwords or emails
        userViewModel.getUser(email, password) { user ->
            if (user != null) {
                onLoginSuccess(email, user.uid)
            } else {
                dialogMessage = "Invalid email or password!"
                showDialog = true
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text(text = "Login Status") },
            text = { Text(text = dialogMessage) }
        )
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "Pizza Background",
            modifier = Modifier.fillMaxSize().graphicsLayer(alpha = 0.5f),
            contentScale = ContentScale.FillBounds
        )

        LoginScreenStateless(
            email = emailState.value,
            onEmailChange = { emailState.value = it },
            password = passwordState.value,
            onPasswordChange = { passwordState.value = it },
            onLoginClick = { handleLogin(emailState.value, passwordState.value) },
            isButtonEnabled = emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()
        )
    }
}

@Composable
fun LoginScreenStateless(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
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
            text = "Login",
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
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
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
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth().size(60.dp),
            enabled = isButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Login", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

