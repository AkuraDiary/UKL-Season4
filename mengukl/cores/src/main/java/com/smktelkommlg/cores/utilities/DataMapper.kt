package com.smktelkommlg.cores.utilities

import com.asthiseta.core.data.soure.remote.response.UserResponse
import com.smktelkommlg.cores.data.source.remote.response.ItemResponse
import com.smktelkommlg.cores.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
//    fun mapResponsesToDomain(input: List<ItemResponse>) : Flow<List<Item>> {
//        val dataArray = ArrayList<Item>()
//        input.map{
//            val item = Item(
//                it._id,
//                it.name,
//                it.description,
//                it.address,
//                it.imageUrl,
//                it.genderRestriction,
//                it.available_bedrooms,
//                it.total_bedrooms,
//                it.price,
//            )
//            dataArray.add(item)
//        }
//        return flowOf(dataArray)
//    }

    fun mapResponseToDomain(input: List<ItemResponse>): Flow<Item> {
        return flowOf(
            Item(
                input[0]._id,
                input[0].name,
                input[0].description,
                input[0].address,
                input[0].imageUrl,
                input[0].genderRestriction,
                input[0].available_bedrooms,
                input[0].total_bedrooms,
                input[0].price,
            )
        )
    }


    fun mapResponsesToDomain(input: List<UserResponse>): Flow<List<User>> {
        val dataArray = ArrayList<User>()
        input.map {
            val user = User(
                it.id,
                it.login,
                it.url,
                it.avatarUrl,
                it.name,
                it.location,
                it.type,
                it.publicRepos,
                it.followers,
                it.following
            )
            dataArray.add(user)
        }
        return flowOf(dataArray)
    }

    fun mapResponseToDomain(input: UserResponse): Flow<User> {
        return flowOf(
            User(
                input.id,
                input.login,
                input.url,
                input.avatarUrl,
                input.name,
                input.location,
                input.type,
                input.publicRepos,
                input.followers,
                input.following
            )
        )
    }

}