package com.arthur.joke_app

import com.arthur.joke_app.data.repository.joke_repository.repositorys.JokeFakeRepository
import com.arthur.joke_app.ui.screens.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun downloadJokeCorrect() {

        val viewModel = HomeViewModel(jokeRepository = JokeFakeRepository())

        //assert(viewModel.uiState.value.joke == null)
        assert(viewModel.uiState.value.joke != null)
        assertEquals(viewModel.uiState.value.joke?.id, 0)

    }

}