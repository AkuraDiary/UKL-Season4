package com.smktelkommlg.cores.domain.usecase

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User

import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUsers(): Flow<Resource<List<User>>>

    fun getAllFollowers(username: String): Flow<Resource<List<User>>>

    fun getAllFollowing(username: String): Flow<Resource<List<User>>>

    fun getDetailUser(username: String): Flow<Resource<User>>
}