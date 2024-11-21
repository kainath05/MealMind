package com.example.mealmind.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mealmind.LocalNavController
import com.example.mealmind.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar() {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (currentRoute != "home_screen") {
                    IconButton(
                        onClick = { navController.popBackStack() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.backbutton),
                            contentDescription = "Back Button"
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1.0f))

                Text(
                    text = "MealMind",
                    style = MaterialTheme.typography.displayLarge,
                )

                Spacer(modifier = Modifier.weight(1.0f))

                if (currentRoute != "home_screen") { //to make it so the settings and back button is not on home screen
                    IconButton(
                        onClick = { navController.navigate("settings_screen") },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Settings Button"
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
