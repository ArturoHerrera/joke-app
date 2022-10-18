package com.arthur.joke_app.data.repository.joke_repository.repositorys

import com.arthur.joke_app.data.remote.dto.JokeResponse
import kotlinx.coroutines.flow.Flow

class JokeFakeRepository : JokeTasks {

    override suspend fun getJoke(): Flow<Pair<String?, JokeResponse?>> {
        TODO("Not yet implemented")
    }
}