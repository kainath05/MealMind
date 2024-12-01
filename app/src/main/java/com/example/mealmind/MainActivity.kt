package com.example.mealmind

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealmind.components.ScaffoldTopBar
import com.example.mealmind.data.SharedViewModel
import com.example.mealmind.openAi.ResponseScreen
import com.example.mealmind.screens.*
import com.example.mealmind.ui.theme.MealMindTheme
import io.ktor.utils.io.concurrent.shared

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            val sharedViewModel: SharedViewModel = viewModel() // Create the shared ViewModel

            MealMindTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        topBar = { ScaffoldTopBar() },
                        content = { paddingValues ->
                            NavigationHost(
                                modifier = Modifier.padding(paddingValues),
                                navController = navController,
                                onDarkTheme = { isDarkTheme = !isDarkTheme },
                                sharedViewModel = sharedViewModel
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
    onDarkTheme: () -> Unit,
    sharedViewModel: SharedViewModel
) {
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
                onLoginSuccess = { email, userId ->
                    sharedViewModel.updateEmail(email)
                    sharedViewModel.updateUserId(userId)
                    navController.navigate("profile_screen")
                }
            )
        }
        composable("register_screen") {
            RegisterScreenStateful(
                modifier = modifier,
                onRegisterSuccess = { navController.navigate("login_screen") }
            )
        }
        composable("form_screen") {
            StatefulFormScreen(
                modifier = modifier,
                onSubmit = { navController.navigate("recipes_screen") } ,
                sharedViewModel = sharedViewModel
            )
        }
        composable("profile_screen") {
            ProfileScreen(
                modifier = modifier,
                onPreference = { navController.navigate("form_screen") },
                onRecipe = {
                    println("Navigating to recipes_screen")
                    navController.navigate("recipes_screen")
                },
                email = sharedViewModel.email,
                sharedViewModel = sharedViewModel
            )
        }
        composable("recipes_screen") {
            RecipesScreen(
                modifier = modifier,
                userId = sharedViewModel.userId,
                onNavigateToDetails = { recipeName ->
                    val encodedName = Uri.encode(recipeName)
                    navController.navigate("details_screen/$encodedName")
                }
            )
        }
        composable(
            route = "details_screen/{recipeName}",
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeName = Uri.decode(backStackEntry.arguments?.getString("recipeName"))
            RecipeDetailsScreen(recipeName = recipeName, sharedViewModel = sharedViewModel)
        }
        composable("settings_screen") {
            SettingsScreen(
                modifier = modifier,
                onDarkThemeToggle = onDarkTheme,
                onNavigateToProfile = { navController.navigate("profile_screen") }
            )
        }
    }
}

