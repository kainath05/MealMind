package com.example.mealmind.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mealmind.LocalNavController
import com.example.mealmind.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar() {
    val navController = LocalNavController.current

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1.5f)) //making it exactly in the middle

                Text(
                    text = "MealMind",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.weight(4f)
                )

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.backbutton),
                        contentDescription = "Back Button"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
