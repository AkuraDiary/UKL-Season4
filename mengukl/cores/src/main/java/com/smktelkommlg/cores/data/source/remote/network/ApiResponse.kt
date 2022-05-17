package com.smktelkommlg.cores.data.source.remote.network

sealed class ApiResponse<out T> {
    data class IsSucces<out T>(val data: T) : ApiResponse<T>()
    data class IsError(val errorMessage: String) : ApiResponse<Nothing>()
}