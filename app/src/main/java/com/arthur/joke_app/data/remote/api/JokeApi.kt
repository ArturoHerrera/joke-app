package com.arthur.joke_app.data.remote.api

import com.arthur.joke_app.data.model.JokeCategoryEnum
import com.arthur.joke_app.data.model.JokeLang
import com.arthur.joke_app.data.remote.dto.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeApi {

    @GET("/joke/{subject}")
    suspend fun getJokes(
        @Path("subject") subject: String = JokeCategoryEnum.Any.name,
        @Query("lang") lang: String = JokeLang.values().random().name
    ): Response<JokeResponse>

}