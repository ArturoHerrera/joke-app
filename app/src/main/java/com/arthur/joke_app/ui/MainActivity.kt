package com.arthur.joke_app.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arthur.joke_app.navigation.Destinations
import com.arthur.joke_app.navigation.JokeAppNavGraph
import com.arthur.joke_app.ui.screens.login.LoginViewModel
import com.arthur.joke_app.ui.theme.JokeAppTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            //JokeApp()
            JokeAppTheme {
                navController = rememberNavController()
                Scaffold {
                    JokeAppNavGraph(
                        navController = navController
                    )
                    checkAuthState()
                }

            }
        }
    }

    private fun checkAuthState() {
        if(viewModel.isUserAuthenticated) {
            navigateToProfileScreen()
        }
    }

    private fun navigateToProfileScreen() = navController.navigate(Destinations.HOME_SCREEN)
}
