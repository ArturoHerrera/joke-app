package com.arthur.joke_app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.joke_app.core.FirebaseAuthResponse
import com.arthur.joke_app.data.repository.AuthRepository
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {

    private val vmUiState = MutableStateFlow(LoginUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    //TODO Improve the way of saving user data. Room or sharedPreferences.
    val isUserAuthenticated = authRepository.isUserAuthenticatedInFirebase
    val userDisplayName = authRepository.userDisplayName
    val userPhotoUrl = authRepository.userPhotoUrl

    init {
        viewModelScope.launch {
            setUser(
                isUserAuthenticated = authRepository.isUserAuthenticatedInFirebase,
                userDisplayName = authRepository.userDisplayName,
                userPhotoUrl = authRepository.userPhotoUrl
            )
        }
    }

    private fun setUser(isUserAuthenticated: Boolean, userDisplayName: String, userPhotoUrl: String){
        vmUiState.update {
            it.copy(
                isUserAuthenticated = isUserAuthenticated,
                displayName = userDisplayName,
                photoUrl = userPhotoUrl
            )
        }
    }

    fun clearUiState(){
        vmUiState.update {
            it.copy(
                isUserAuthenticated = false,
                displayName = "",
                photoUrl = "",
                oneTapSignInResponse = FirebaseAuthResponse.Success(data = null),
                signInWithGoogleResponse = FirebaseAuthResponse.Success(data = false),
                signOutResponse = FirebaseAuthResponse.Success(data = false),
                revokeAccessResponse = FirebaseAuthResponse.Success(data = false)
            )
        }
    }

    fun oneTapSignIn() = viewModelScope.launch {
        authRepository.oneTapSignInWithGoogle().collect { response ->
            vmUiState.update {
                it.copy(oneTapSignInResponse = response,)
            }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        authRepository.firebaseSignInWithGoogle(googleCredential).collect { response ->
            vmUiState.update {
                it.copy(signInWithGoogleResponse = response,)
            }
        }
    }

    fun signOut() = viewModelScope.launch {
        authRepository.signOut().collect { response ->
            vmUiState.update {
                it.copy(signOutResponse = response,)
            }
        }
    }

}