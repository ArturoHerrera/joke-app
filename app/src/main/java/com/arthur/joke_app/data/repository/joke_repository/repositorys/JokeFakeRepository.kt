package com.arthur.joke_app.data.repository.joke_repository.repositorys

import com.arthur.joke_app.data.remote.dto.JokeFlags
import com.arthur.joke_app.data.remote.dto.JokeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeFakeRepository : JokeTasks {
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