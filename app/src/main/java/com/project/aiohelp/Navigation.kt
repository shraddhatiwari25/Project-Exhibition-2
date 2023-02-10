package com.project.aiohelp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.UserMain.route) {
        composable(route = Screen.SetupScreen.route) {
            Setup(navController = navController)
        }
        composable(route = Screen.WorkerForm.route) {
            WorkerForm(navController = navController)
        }
        composable(route = Screen.UserForm.route) {
            UserForm(navController = navController)
        }
        composable(route = Screen.UserMain.route) {
            UserMain(navController = navController)
        }
        composable(
            route = Screen.WorkerList.route + "/{job}",
            arguments = listOf(navArgument("job") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            entry.arguments?.getString("job")
                ?.let { WorkerList(navController = navController, job = it) }
        }
        composable(
            route = Screen.WorkerInfo.route + "/{name}/{age}/{email}/{phNo}/{rating}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("age") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("phNo") { type = NavType.StringType }
            )
        ) { entry ->
            WorkerInfo(
                navController = navController,
                name = entry.arguments?.getString("name"),
                age = entry.arguments?.getString("age"),
                email = entry.arguments?.getString("email"),
                phoneNo = entry.arguments?.getString("phNo"),
                rating = entry.arguments?.getString("rating"),
            )
        }
        composable(
            route = Screen.BookingPage.route + "/{name}/{email}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType })
        ) { entry ->
            BookingPage(
                navController = navController,
                entry.arguments?.getString("name"),
                entry.arguments?.getString("email")
            )
        }
        composable(
            route = Screen.OrdersPage.route + "/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })
        ) {entry ->
            OrdersPage(navController = navController, entry.arguments?.getString("userEmail"))
        }
    }
}
