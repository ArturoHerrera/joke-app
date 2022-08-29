package com.arthur.joke_app.data.repository.remote_data_source

import com.arthur.joke_app.data.model.JokeCategoryEnum
import com.arthur.joke_app.data.remote.api.JokeApi
import com.arthur.joke_app.data.remote.dto.JokeResponse
import com.arthur.joke_app.data.repository.JokeRemoteDataSource
import com.arthur.joke_app.utils.HttpError
import com.arthur.joke_app.utils.ServiceResult

class JokeRetrofitRemoteDataSource(
    private val jokeApi: JokeApi
) : JokeRemoteDataSource {
    override suspend fun getJoke(subject: JokeCategoryEnum): ServiceResult<JokeResponse> {
        val response = jokeApi.getJokes(subject = subject.name)
        return if (response.isSuccessful) {
            ServiceResult.Success(response.body(), response.code())
        } else {
            ServiceResult.Error(HttpError.fromCode(response.code()).errorMsg, response.code())
        }
    }

}