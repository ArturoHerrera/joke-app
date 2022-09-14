package com.arthur.joke_app.ui.screens.home

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.joke_app.ui.components.*
import com.arthur.joke_app.ui.screens.login.LoginViewModel
import com.arthur.joke_app.utils.ExtFunctions.gradientBackground
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.random.Random

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navigateFromHomeToLogin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val uiState by viewModel.uiState.collectAsState()
    val loginUiState by loginViewModel.uiState.collectAsState()

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        coroutineScope.launch {
            scaffoldState.drawerState.close()
        }
    }

    BackHandler(enabled = scaffoldState.drawerState.isClosed) {
        coroutineScope.launch {
            activity?.let{ it.finish()}
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            if (loginUiState.isUserAuthenticated) {
                HomeProfileDrawer(
                    userDisplayName = loginUiState.displayName,
                    userPhotoUrl = loginUiState.photoUrl,
                    onLogOut = loginViewModel::signOut
                )
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (uiState.loading) {
                    ProgressBar()
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .gradientBackground(
                            listOf(
                                Color(
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    Random.nextInt(256)
                                ), Color(
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    Random.nextInt(256)
                                )
                            ), angle = 45f
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                if (!uiState.loading) {
                                    viewModel.getJoke()
                                }
                            }
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        uiState.joke?.let { safeJoke ->
                            JokeComponent(joke = safeJoke)
                            ShareJokeComponent(joke = safeJoke, onShareClicked = {
                                val jokeText =
                                    safeJoke.joke ?: (safeJoke.setup + " \n\n" + safeJoke.delivery)
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.putExtra(Intent.EXTRA_TEXT, jokeText)
                                intent.type = "text/plain"
                                startActivity(
                                    context,
                                    Intent.createChooser(intent, "Share Via"),
                                    null
                                )
                            })
                        }
                    }

                }
            }
        }
    )

    loginUiState.signOutResponse?.let { safeSignOutResponse ->
        SignOut(
            signOutResponse = safeSignOutResponse,
            navigateToAuthScreen = { signedOut ->
                if (signedOut) {
                    loginViewModel.clearUiState()
                    LaunchedEffect(signedOut) {
                        navigateFromHomeToLogin()
                    }
                }
            }
        )
    }
}