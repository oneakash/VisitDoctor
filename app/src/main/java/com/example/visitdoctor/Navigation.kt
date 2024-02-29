package com.example.visitdoctor

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation(){
    var navHostController = rememberNavController()
    NavHost(
        navController = navHostController, startDestination = Screen.Greet.route
    ){
        composable(Screen.Greet.route){
            Greeting(navHostController)
        }
        composable(Screen.First.route){
            SignUp(navHostController,)
        }
        composable(Screen.Second.route){
            SignIn(navHostController)
        }
        composable(Screen.Dashboard.route){
            UserDashboard(navHostController)
        }
    }
}

sealed class Screen(val route:String){
    object First : Screen("first")
    object Second : Screen("second")
    object Greet : Screen("Greet")
    object Dashboard : Screen("dashboard")
}