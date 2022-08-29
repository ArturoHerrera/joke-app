package com.arthur.joke_app.ui.screens.home

import com.arthur.joke_app.data.remote.dto.JokeResponse

data class HomeUiState(
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val joke: JokeResponse? = null
)