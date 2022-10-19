package com.arthur.joke_app.data.repository.joke_repository.repositorys

import com.arthur.joke_app.core.ServiceResult
import com.arthur.joke_app.core.getDto
import com.arthur.joke_app.core.getMessage
import com.arthur.joke_app.core.succeeded
import com.arthur.joke_app.data.model.JokeCategoryEnum
import com.arthur.joke_app.data.remote.dto.JokeResponse
import kotlinx.coroutines.CloseableCoroutineDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GoodJokeRepository(
    private val jokeRemoteDS: JokeRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokeTasks {

    override suspend fun getJoke(): Flow<Pair<String?, JokeResponse?>> = flow {
        emit(jokeRemoteDS.getJoke())
    }
        .map { result ->
            if (result.succeeded) return@map Pair(null, result.getDto())
            Pair(result.getMessage(), null)
        }
        .filterNotNull()
        .catch { e -> e.printStackTrace() }
        .flowOn(dispatcher)

}

interface JokeRemoteDataSource {
    suspend fun getJoke(subject: JokeCategoryEnum = JokeCategoryEnum.Any): ServiceResult<JokeResponse>
}