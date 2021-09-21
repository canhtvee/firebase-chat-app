package com.canhtv.ee.firebasechatapp.utils

sealed class Result<T> {
    data class Success<T>(val data: T)       : Result<T>()
    data class Error<T>(val message: String) : Result<T>()
}