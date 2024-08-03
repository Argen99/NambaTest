package com.example.data.core

sealed class Either<out R> {
    data class Success<out T>(val data: T) : Either<T>()
    data class Error(val message: String) : Either<Nothing>()
}