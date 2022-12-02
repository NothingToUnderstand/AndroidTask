package com.example.androidtask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidtask.MainViewModel
import com.example.androidtask.screens.DetailsScreen
import com.example.androidtask.screens.MainScreen
import com.example.androidtask.screens.SplashScreen


@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable(route = "splash_screen") {
            SplashScreen(navController, viewModel)
        }
        composable(route ="main_screen") {
            MainScreen(viewModel,navController)
        }
        composable(route = "details_screen/{Id}") { backStackEntry ->
            DetailsScreen(viewModel,backStackEntry
                .arguments?.getString("Id")?:"1")
        }
    }
}