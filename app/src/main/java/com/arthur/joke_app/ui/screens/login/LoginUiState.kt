package com.arthur.joke_app.ui.screens.login

import com.arthur.joke_app.core.FirebaseAuthResponse
import com.arthur.joke_app.core.FirebaseAuthResponse.Success
import com.google.android.gms.auth.api.identity.BeginSignInResult

data class LoginUiState(
    val isUserAuthenticated: Boolean = false,
    val displayName: String = "",
    val photoUrl: String = "",
    val oneTapSignInResponse: FirebaseAuthResponse<BeginSignInResult> = Success(data = null),
    val signInWithGoogleResponse: FirebaseAuthResponse<Boolean> = Success(data = false),
    val signOutResponse: FirebaseAuthResponse<Boolean> = Success(data = false),
    val revokeAccessResponse: FirebaseAuthResponse<Boolean> = Success(data = false)
)