package net.hanan.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import net.hanan.core.navigation.Route
import net.hanan.core_ui.ui.theme.CatsTheme
import net.hanan.details.presenntation.CatDetailsScreen
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
                        HomeScreen(
                            onCatClicked = { catId ->
                                navController.navigate(
                                    Route.DETAILS + "/$catId"
                                )
                            }
                        )
                    }
                    composable(
                        route = Route.DETAILS + "/{catId}",
                        arguments = listOf(
                            navArgument("catId") {
                                type = NavType.StringType
                            },
                        )
                    ) {
                        val catId = it.arguments?.getString("catId")!!
                        CatDetailsScreen(
                            catId = catId
                        )
                    }
                }
            }
        }
    }
}