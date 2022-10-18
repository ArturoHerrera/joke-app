package com.arthur.joke_app.core

import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> networkCall(
    call: suspend () -> T
): ServiceResult<T> = try {
    ServiceResult.Success(dto = call())
} catch (e: UnknownHostException) {
    ServiceResult.Error(message = "Revisa tu conexión a internet.")
    //TODO Mejorar sistema de errores. Mover a stringResources.
} catch (e: HttpException) {
    ServiceResult.Error(message = HttpError.fromCode(e.code()).errorMsg)
} catch (e: Exception) {
    ServiceResult.Error(message = "Ocurrio un problema inesperado")
}