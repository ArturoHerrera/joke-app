package com.arthur.joke_app.ui.screens.home

import android.content.Intent
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()
    val loginUiState by loginViewModel.uiState.collectAsState()

    val context = LocalContext.current

    Log.i("testUser", "HomeScreen isUserAuthenticated -> ${loginViewModel.isUserAuthenticated}")
    Log.i("testUser", "HomeScreen userDisplayName -> ${loginViewModel.userDisplayName}")
    Log.i("testUser", "HomeScreen userPhotoUrl -> ${loginViewModel.userPhotoUrl}")

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            if(loginUiState.isUserAuthenticated){
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
                            ),
                            angle = 45f
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
                                val jokeText = safeJoke.joke ?: ( safeJoke.setup + " \n\n" + safeJoke.delivery )
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.putExtra(Intent.EXTRA_TEXT, jokeText)
                                intent.type = "text/plain"
                                startActivity(context, Intent.createChooser(intent, "Share Via"), null)
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
                    LaunchedEffect(signedOut) {
                        navigateFromHomeToLogin()
                    }
                }
            }
        )
    }
}