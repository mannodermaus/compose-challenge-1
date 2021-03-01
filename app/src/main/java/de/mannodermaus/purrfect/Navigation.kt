package de.mannodermaus.purrfect

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import de.mannodermaus.purrfect.view.detail.DetailScreen
import de.mannodermaus.purrfect.view.list.ListScreen

@Composable
fun KittenNavHost(controller: NavHostController) {
    NavHost(navController = controller, startDestination = "list") {
        composable("list") {
            ListScreen(controller)
        }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            DetailScreen(
                kittenId = it.arguments?.getString("id")!!
            )
        }
    }
}
