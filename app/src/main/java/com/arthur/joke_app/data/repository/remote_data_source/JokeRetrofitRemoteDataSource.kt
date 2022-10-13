package com.arthur.joke_app.data.repository.remote_data_source

import com.arthur.joke_app.core.ServiceResult
import com.arthur.joke_app.core.networkCall
import com.arthur.joke_app.data.model.JokeCategoryEnum
import com.arthur.joke_app.data.remote.api.JokeApi
import com.arthur.joke_app.data.remote.dto.JokeResponse
import com.arthur.joke_app.data.repository.JokeRemoteDataSource

class JokeRetrofitRemoteDataSource(
    private val jokeApi: JokeApi
) : JokeRemoteDataSource {

    override suspend fun getJoke(subject: JokeCategoryEnum): ServiceResult<JokeResponse> =
        networkCall {
            jokeApi.getJokes(subject = subject.name).body()!!
        }

}