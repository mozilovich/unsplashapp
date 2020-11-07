package com.app.unsplashapp.presentation.extensions

import com.app.unsplashapp.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun <T> Flow<Result<T>>.applyLoading(): Flow<Result<T>> {
    return this.onStart { emit(Result.startLoading()) }
        .onCompletion { emit(Result.endLoading()) }
}

inline fun <T, R> Flow<Result<List<T>>>.mapFlatten(crossinline transform: (value: T) -> R): Flow<Result<List<R>>> =
    flow {
        collect {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    emit(Result.success(it.data?.map(transform) ?: emptyList()))
                }
                Result.Status.LOADING -> {
                    emit(if (it.isLoading) Result.startLoading<List<R>>() else Result.endLoading())
                }
                else -> emit(Result.error<List<R>>(it.error))
            }
        }
    }