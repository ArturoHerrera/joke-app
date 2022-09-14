package com.arthur.joke_app.data.repository.remote_data_source

import com.arthur.joke_app.core.AppConstants.CREATED_AT
import com.arthur.joke_app.core.AppConstants.DISPLAY_NAME
import com.arthur.joke_app.core.AppConstants.EMAIL
import com.arthur.joke_app.core.AppConstants.NO_DISPLAY_NAME
import com.arthur.joke_app.core.AppConstants.PHOTO_URL
import com.arthur.joke_app.core.AppConstants.SIGN_IN_REQUEST
import com.arthur.joke_app.core.AppConstants.SIGN_UP_REQUEST
import com.arthur.joke_app.core.AppConstants.USERS
import com.arthur.joke_app.core.FirebaseAuthResponse.*
import com.arthur.joke_app.data.repository.AuthRemoteDataSource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Named

class AuthFirebaseRemoteDataSource(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST) private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST) private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
    private val db: FirebaseFirestore
) : AuthRemoteDataSource {

    override val isUserAuthenticatedInFirebase = auth.currentUser != null
    override val userDisplayName = auth.currentUser?.displayName ?: NO_DISPLAY_NAME
    override val userPhotoUrl = auth.currentUser?.photoUrl.toString()

    override fun oneTapSignInWithGoogle() = flow {
        try {
            emit(Loading)
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            emit(Success(signInResult))
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                emit(Success(signUpResult))
            } catch (e: Exception) {
                emit(Failure(e))
            }
        }
    }

    override fun firebaseSignInWithGoogle(googleCredential: AuthCredential) = flow {
        try {
            emit(Loading)
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    override fun signOut() = flow {
        try {
            emit(Loading)
            oneTapClient.signOut().await()
            auth.signOut()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to serverTimestamp()
)