package com.arthur.joke_app.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.joke_app.core.FirebaseAuthResponse
import com.arthur.joke_app.core.FirebaseAuthResponse.*
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.gson.Gson
import kotlin.random.Random

@Composable
fun OneTapSignIn(
    oneTapSignInResponse: FirebaseAuthResponse<BeginSignInResult>,
    launch: @Composable (result: BeginSignInResult) -> Unit
) {
    Log.i("testLogin", "- OneTapSignIn enter -")
    Log.i("testLogin", "- OneTapSignIn uiState.oneTapSignInResponse -> ${Gson().toJson(oneTapSignInResponse)}")
    when(val oneTapSignInResponse = oneTapSignInResponse) {
        is Loading -> ProgressBar()
        is Success -> oneTapSignInResponse.data?.let {
            launch(it)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }
}

@Composable
fun SignInWithGoogle(
    signInWithGoogleResponse: FirebaseAuthResponse<Boolean>,
    navigateToHomeScreen: @Composable (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = signInWithGoogleResponse) {
        is Loading -> ProgressBar()
        is Success -> signInWithGoogleResponse.data?.let { signedIn ->
            navigateToHomeScreen(signedIn)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}

@Composable
fun SignOut(
    signOutResponse: FirebaseAuthResponse<Boolean>,
    navigateToAuthScreen: @Composable (signedOut: Boolean) -> Unit
) {
    when(val signOutResponse = signOutResponse) {
        is Loading -> ProgressBar()
        is Success -> signOutResponse.data?.let { signedOut ->
            navigateToAuthScreen(signedOut)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(signOutResponse.e)
        }
    }
}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
            color = Color(
                Random.nextInt(1, 256),
                Random.nextInt(1, 256),
                Random.nextInt(1, 256)
            ),
            backgroundColor = Color.Black
        )
    }
}


