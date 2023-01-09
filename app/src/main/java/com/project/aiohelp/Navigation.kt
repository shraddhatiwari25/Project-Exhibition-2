package com.project.aiohelp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SetupScreen.route) {
        composable(route = Screen.SetupScreen.route){
            Setup(navController = navController)
        }
        composable(route = Screen.WorkerForm.route){
            WorkerForm(navController = navController)
        }
        composable(route = Screen.UserForm.route){
            UserForm(navController = navController)
        }
        composable(route = Screen.UserMain.route){
            UserMain(navController = navController)
        }
    }
}