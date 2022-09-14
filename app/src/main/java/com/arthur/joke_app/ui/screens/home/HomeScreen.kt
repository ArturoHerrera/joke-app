package com.arthur.joke_app.ui.screens.home

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.joke_app.ui.components.JokeComponent
import com.arthur.joke_app.ui.components.ShareJokeComponent
import com.arthur.joke_app.utils.ExtFunctions.gradientBackground
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navigateToView: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Surface() {
                if (uiState.loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = MaterialTheme.colors.secondary
                    )
                }
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
}