package com.arthur.joke_app.data.repository

import com.arthur.joke_app.data.model.JokeCategoryEnum
import com.arthur.joke_app.data.remote.dto.JokeResponse
import com.arthur.joke_app.core.ServiceResult
import com.arthur.joke_app.core.getDto
import com.arthur.joke_app.core.getMessage
import com.arthur.joke_app.core.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class JokeRepositoryRepository(
    private val jokeRemoteDS: JokeRemoteDataSource
) {

    suspend fun getJoke(): Flow<Pair<String?, JokeResponse?>> = flow {
        emit(jokeRemoteDS.getJoke())
    }
        .map { result ->
            if (result.succeeded) return@map Pair(null, result.getDto())
            Pair(result.getMessage(), null)
        }
        .filterNotNull()
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)

}

interface JokeRemoteDataSource {
    suspend fun getJoke(subject: JokeCategoryEnum = JokeCategoryEnum.Any): ServiceResult<JokeResponse>
}