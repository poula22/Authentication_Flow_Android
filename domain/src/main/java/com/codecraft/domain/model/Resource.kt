package com.codecraft.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.Exception

sealed interface Resource<out T>{
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable) : Resource<Nothing>
    object Loading : Resource<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(exception = it)) }
}
