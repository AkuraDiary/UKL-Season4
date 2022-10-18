package com.smktelkommlg.cores.domain.repository

import com.asthiseta.core.data.Resource
import com.smktelkommlg.cores.domain.model.User

import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAllUsers(): Flow<Resource<List<User>>>

    fun getAllFollowers(username: String): Flow<Resource<List<User>>>

    fun getAllFollowing(username: String): Flow<Resource<List<User>>>

    //fun getDetailUser(username: String): Flow<Resource<User>>

    //fun getDetailState(username: String): Flow<User>?
    fun getDetailUser(username: String): Flow<Resource<User>>
}
