package net.hanan.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.hanan.cats.ui.theme.CatsTheme
import net.hanan.core.navigation.Route
import net.hanan.home.presentation.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.HOME
                ) {
                    composable(Route.HOME) {
                        HomeScreen()
                    }
                    composable(Route.DETAILS) {

                    }
                }
            }
        }
    }
}