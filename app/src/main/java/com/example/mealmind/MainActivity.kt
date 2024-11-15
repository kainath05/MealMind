package com.example.mealmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealmind.components.ScaffoldTopBar
import com.example.mealmind.data.PreferenceViewModel
import com.example.mealmind.data.PreferenceViewModelFactory
import com.example.mealmind.data.database.AppDatabase
import com.example.mealmind.screens.*
import com.example.mealmind.ui.theme.MealMindTheme

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealMindTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        topBar = { ScaffoldTopBar() },
                        content = { paddingValues ->
                            NavigationHost(
                                modifier = Modifier.padding(paddingValues),
                                navController = navController
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController,
    preferenceViewModel: PreferenceViewModel = viewModel(factory = PreferenceViewModelFactory(AppDatabase.getDatabase(LocalContext.current).preferenceDao()))) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(
                modifier = modifier,
                onGetStartedClick = { navController.navigate("register_screen") },
                onLoginClick = { navController.navigate("login_screen") }
            )
        }
        composable("login_screen") {
            LoginScreenStateful(
                modifier = modifier,
                onLoginSuccess = { navController.navigate("profile_screen") }
            )
        }
        composable("register_screen") {
            RegisterScreenStateful(
                modifier = modifier,
                onRegisterSuccess = { navController.navigate("form_screen") }
            )
        }
        composable("form_screen") {
            StatefulFormScreen(
                modifier = modifier,
                onSubmit = { preference ->
                    navController.navigate("recipes_screen")
                    preferenceViewModel.insert(preference)
                }
            )
        }
        composable("profile_screen") {
            ProfileScreen(
                modifier = modifier,
                onPreference = { navController.navigate("form_screen") },
                onRecipe = { navController.navigate("recipes_screen") }
            )
        }
        composable("recipes_screen") {
            RecipesScreen(
                modifier = modifier,
                onDetails = { navController.navigate("details_screen") }
            )
        }
        composable("details_screen") {
            RecipeDetails(modifier = modifier)
        }
    }
}
