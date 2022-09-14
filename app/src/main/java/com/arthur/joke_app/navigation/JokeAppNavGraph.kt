package com.arthur.joke_app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arthur.joke_app.ui.screens.home.HomeScreen
import com.arthur.joke_app.ui.screens.login.LoginScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val SPLASH_SCREEN = "splash"
    const val HOME_SCREEN = "home"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun JokeAppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.LOGIN_SCREEN
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.LOGIN_SCREEN) {
            LoginScreen(
                navigateToHome = actions.navigateToHome,
            )
        }
        composable(Destinations.HOME_SCREEN) {
            HomeScreen(
                navigateToView = actions.navigateToHome,
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(Destinations.HOME_SCREEN)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}