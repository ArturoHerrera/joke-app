package com.arthur.joke_app.data.repository.joke_repository.repositorys

import com.arthur.joke_app.data.remote.dto.JokeResponse
import kotlinx.coroutines.flow.Flow

interface JokeTasks {

    /*
    *   Al tener nuestro manifiesto de tareas,
    *   podemos implementar esta interface en un
    *   repositorio de prueba, para facilitar
    *   el testing.
    */

    suspend fun getJoke(): Flow<Pair<String?, JokeResponse?>>

}