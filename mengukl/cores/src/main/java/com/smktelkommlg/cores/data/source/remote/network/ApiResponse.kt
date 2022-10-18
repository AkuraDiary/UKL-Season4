package com.asthiseta.core.data.soure.remote.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage : String?) : ApiResponse<Nothing>()
}