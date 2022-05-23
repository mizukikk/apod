package com.example.apod.api

sealed class HttpResult<out T : Any> {
    class Success<out T : Any>(val response: T) : HttpResult<T>()
    class Error(val throws: Throwable) : HttpResult<Nothing>()
}