//package com.example.mealmind.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.compositionLocalOf
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.mealmind.screens.*
//
//val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }
//
//@Composable
//fun NavigationHost(modifier: Modifier) {
//    val navController = rememberNavController()
//
//    CompositionLocalProvider(
//        LocalNavController provides navController
//    ) {
//        NavHost(navController = navController, startDestination = "home_screen") {
//            composable("home_screen") {
//                HomeScreen(
//                    modifier,
//                    onGetStartedClick = { navController.navigate("register_screen") },
//                    onLoginClick = { navController.navigate("login_screen") }
//                )
//            }
//            composable("login_screen") {
//                LoginScreenStateful(modifier, onLoginSuccess = { navController.navigate("profile_screen") })
//            }
//            composable("register_screen") {
//                RegisterScreenStateful(modifier, onRegisterSuccess = { navController.navigate("form_screen") })
//            }
//            composable("form_screen") {
//                StatefulFormScreen(modifier)
//            }
//            composable("profile_screen") {
//                ProfileScreen(modifier, onPreference = { navController.navigate("form_screen") })
//            }
//        }
//    }
//}
