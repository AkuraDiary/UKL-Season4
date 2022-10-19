package com.smkelkommlg.core.data

import com.smktelkommlg.cores.data.source.remote.RemoteDataSource
import com.smkelkommlg.core.data.soure.remote.network.ApiResponse
import com.smktelkommlg.cores.data.source.remote.response.UserResponse
import com.smktelkommlg.cores.data.NetOnlyResource
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User
import com.smktelkommlg.cores.domain.repository.IUserRepository
import com.smktelkommlg.cores.utilities.DataMapper

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*


class UserRepository(private val remoteDataSource : RemoteDataSource) : IUserRepository {
    override fun getAllUsers(): Flow<Resource<List<User>>> {
        return object : NetOnlyResource<List<User>, List<UserResponse>>(),
            Flow<Resource<List<User>>> {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllUsers()
            }

            @InternalCoroutinesApi
            override suspend fun collect(collector: FlowCollector<Resource<List<User>>>) {
                TODO("Not yet implemented")
            }
        }.asFlow()
    }

    override fun getAllFollowers(username: String): Flow<Resource<List<User>>> {
        return object : NetOnlyResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllFollowers(username)
            }
        }.asFlow()
    }

    override fun getAllFollowing(username: String):Flow<Resource<List<User>>> {
        return object : NetOnlyResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllFollowing(username)
            }
        }.asFlow()
    }

    override fun getDetailUser(username: String): Flow<Resource<User>> {
        return object : NetOnlyResource<User, UserResponse>() {
            override fun loadFromNetwork(data: UserResponse): Flow<User> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.getUserDetail(username)
            }

        }.asFlow()
    }
    /*

    override fun getDetailState(username: String): Flow<User>? {

    }*/
}