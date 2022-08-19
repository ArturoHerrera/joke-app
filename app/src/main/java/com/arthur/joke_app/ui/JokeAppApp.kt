package com.arthur.joke_app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.arthur.joke_app.ui.theme.JokeAppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AndroidTemplateApp() {
    JokeAppTheme {
        val navController = rememberNavController()

        Scaffold {
            JokeAppNavGraph(
                navController = navController
            )
        }

    }
}