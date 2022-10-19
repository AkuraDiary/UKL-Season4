package com.smktelkommlg.cores.domain.usecase

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User
import com.smktelkommlg.cores.domain.repository.IUserRepository

import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getAllUsers(): Flow<Resource<List<User>>> =
        userRepository.getAllUsers()

    override fun getAllFollowers(username: String): Flow<Resource<List<User>>> =
        userRepository.getAllFollowers(username)

    override fun getAllFollowing(username: String): Flow<Resource<List<User>>> =
        userRepository.getAllFollowing(username)

    override fun getDetailUser(username: String): Flow<Resource<User>> = userRepository.getDetailUser(username)

    /*override fun getDetailUser(username: String): Flow<Resource<User>> {

    }*/

}