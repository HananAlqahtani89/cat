package net.hanan.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.hanan.cats.ui.theme.CatsTheme
import net.hanan.core.navigation.Route

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

                    }
                    composable(Route.DETAILS) {

                    }
                }
            }
        }
    }
}