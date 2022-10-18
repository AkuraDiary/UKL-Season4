package com.asthiseta.core.data.soure.remote

import android.util.Log
import com.asthiseta.core.data.soure.remote.network.ApiResponse
import com.asthiseta.core.data.soure.remote.network.ClientAPI
import com.asthiseta.core.data.soure.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RemoteDataSource(private val clientAPI: ClientAPI) {

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userSearch = clientAPI.getAllUser()
                if (userSearch.isNullOrEmpty()) {
                    emit(ApiResponse.Error(null))
                } else {
                    emit(ApiResponse.Success(userSearch))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getAllFollowers(username: String): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userFollower = clientAPI.userFollower(username)
                emit(ApiResponse.Success(userFollower))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getAllFollowing(username: String): Flow<ApiResponse<List<UserResponse>>> =
        flow {
            try {
                val userFollowing = clientAPI.userFollowing(username)
                emit(ApiResponse.Success(userFollowing))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserDetail(username: String): Flow<ApiResponse<UserResponse>> =
        flow {
            try {
                val userDetail = clientAPI.userDetail(username)
                emit(ApiResponse.Success(userDetail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}