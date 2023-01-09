package com.project.aiohelp

sealed class Screen(val route: String) {
    object SetupScreen : Screen("SetupScreen")
    object WorkerForm : Screen("WorkerForm")
    object UserForm : Screen("UserForm")
    object UserMain : Screen("UserMain")
}