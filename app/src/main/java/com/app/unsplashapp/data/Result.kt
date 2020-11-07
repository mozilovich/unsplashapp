package com.app.unsplashapp.data

import java.lang.Exception

data class Result<out T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data)
        }

        fun <T> error(error: Throwable?): Result<T> {
            return Result(Status.ERROR, error = error)
        }

        fun <T> startLoading(): Result<T> {
            return Result(Status.LOADING, isLoading = true)
        }

        fun <T> endLoading(): Result<T> {
            return Result(Status.LOADING, isLoading = false)
        }
    }
}