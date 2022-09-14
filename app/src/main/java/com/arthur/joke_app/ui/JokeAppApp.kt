package com.arthur.joke_app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.arthur.joke_app.navigation.JokeAppNavGraph
import com.arthur.joke_app.ui.theme.JokeAppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AndroidTemplateApp() {
    JokeAppTheme {
        val navController = rememberNavController()

        Scaffold { _ ->
            JokeAppNavGraph(
                navController = navController
            )
        }

    }
}