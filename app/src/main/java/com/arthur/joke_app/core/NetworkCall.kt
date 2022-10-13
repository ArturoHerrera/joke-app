package com.arthur.joke_app.core

import java.net.UnknownHostException

suspend fun <T> networkCall(
    call: suspend () -> T
): ServiceResult<T> = try {
    ServiceResult.Success(dto = call())
} catch (e: UnknownHostException) {
    ServiceResult.Error(message = "Revisa tu conexión a internet.")
    //TODO Mejorar sistema de errores. Mover a stringResources.
} catch (e: Exception) {
    ServiceResult.Error(message = "Ocurrio un problema")
    //TODO Mejorar sistema de errores para obtener código de response.
}