package com.example.greenimpact.navigation

import PlaneScreen
import ServiceScreen
import TransportScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenimpact.SplashScreen
import com.example.greenimpact.food.FoodScreen
import com.example.greenimpact.home.HomeScreen
import com.example.greenimpact.login.LoginScreen
import com.example.quizapp.ui.QuizScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("plane") { PlaneScreen() }
        composable("traffic") { TransportScreen() }
        composable("services") { ServiceScreen() }
        composable("food") { FoodScreen()}
        composable("quiz") { QuizScreen(navController) }
        composable("splash"){ SplashScreen(navController) }



    }
}