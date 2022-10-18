package com.asthiseta.core.data.soure.remote.network


import com.asthiseta.core.data.soure.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientAPI {

    @GET("users")
    suspend fun getAllUser(): List<UserResponse>//ListUserResponse


    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String?
    ): UserResponse

    @GET("users/{username}/followers")
    suspend fun userFollower(
        @Path("username") username: String?
    ): List<UserResponse>

    @GET("users/{username}/following")
    suspend fun userFollowing(
        @Path("username") username: String?
    ): List<UserResponse>
}