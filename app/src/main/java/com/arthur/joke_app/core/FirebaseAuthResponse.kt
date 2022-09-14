package com.arthur.joke_app.core

sealed class FirebaseAuthResponse<out T> {
    object Loading: FirebaseAuthResponse<Nothing>()

    data class Success<out T>(
        val data: T?
    ): FirebaseAuthResponse<T>()

    data class Failure(
        val e: Exception
    ): FirebaseAuthResponse<Nothing>()
}