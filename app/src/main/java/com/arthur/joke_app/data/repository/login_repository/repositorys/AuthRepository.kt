package com.arthur.joke_app.data.repository.login_repository.repositorys

import com.arthur.joke_app.core.FirebaseAuthResponse
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val authRemoteDS: AuthRemoteDataSource
) {

    val isUserAuthenticatedInFirebase = authRemoteDS.isUserAuthenticatedInFirebase
    val userDisplayName = authRemoteDS.userDisplayName
    val userPhotoUrl = authRemoteDS.userPhotoUrl


    fun oneTapSignInWithGoogle() = authRemoteDS.oneTapSignInWithGoogle()

    fun firebaseSignInWithGoogle(googleCredential: AuthCredential) = authRemoteDS.firebaseSignInWithGoogle(googleCredential)

    fun signOut() = authRemoteDS.signOut()

}

interface AuthRemoteDataSource {
    val isUserAuthenticatedInFirebase: Boolean
    val userDisplayName: String
    val userPhotoUrl: String

    fun oneTapSignInWithGoogle(): Flow<FirebaseAuthResponse<BeginSignInResult>>

    fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<FirebaseAuthResponse<Boolean>>

    fun signOut(): Flow<FirebaseAuthResponse<Boolean>>
}

