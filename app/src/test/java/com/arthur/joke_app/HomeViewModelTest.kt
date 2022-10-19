package com.arthur.joke_app

import com.arthur.joke_app.data.remote.dto.JokeFlags
import com.arthur.joke_app.data.remote.dto.JokeResponse
import com.arthur.joke_app.data.repository.joke_repository.repositorys.JokeTasks
import com.arthur.joke_app.ui.screens.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun downloadJokeCorrect() {

        class FakeJokeRepository : JokeTasks {
            override suspend fun getJoke(): Flow<Pair<String?, JokeResponse?>> = flow {
                emit(
                    Pair(
                        null, JokeResponse(
                            error = false,
                            category = "Misc",
                            type = "single",
                            joke = "Una ardilla le pregunta a otra que quiere ser cuando grande, la otra ardilla de responde \\\"Ardila Lülle\\\".",
                            flags = JokeFlags(
                                nsfw = true,
                                religious = false,
                                political = true,
                                racist = false,
                                sexist = false
                            ),
                            id = 0,
                            safe = false,
                            lang = "es"
                        )
                    )
                )
            }
        }

        val viewModel = HomeViewModel(
            jokeRepository = FakeJokeRepository()
        )

        //assert(viewModel.uiState.value.joke == null)
        assert(viewModel.uiState.value.joke != null)
        assertEquals(viewModel.uiState.value.joke?.id, 0)

    }

}