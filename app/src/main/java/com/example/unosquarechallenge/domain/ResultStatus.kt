package com.example.unosquarechallenge.domain

sealed class ResultStatus<T>(val data: T? = null, val message: String? =null){
    class Success<T>(data: T): ResultStatus<T>(data)
    class Error<T>(message: String?, data: T? = null): ResultStatus<T>(data, message )
    class Loading<T>(data: T? = null): ResultStatus<T>(data)
}