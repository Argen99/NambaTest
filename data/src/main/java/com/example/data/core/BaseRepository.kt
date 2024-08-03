package com.example.data.core

import com.example.data.repository.WeatherRepositoryImpl.Companion.UNKNOWN_ERROR_MESSAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<T>> {
        request().also {
            emit(Either.Success(it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        exception.printStackTrace()
        emit(Either.Error(exception.message ?: UNKNOWN_ERROR_MESSAGE))
    }