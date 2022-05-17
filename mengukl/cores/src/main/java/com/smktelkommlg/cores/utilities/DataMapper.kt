package com.smktelkommlg.cores.utilities

import com.smktelkommlg.cores.data.source.remote.response.ItemResponse
import com.smktelkommlg.cores.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(input: List<ItemResponse>) : Flow<List<Item>> {
        val dataArray = ArrayList<Item>()
        input.map{
            val item = Item(
                it._id,
                it.name,
                it.description,
                it.address,
                it.imageUrl,
                it.genderRestriction,
                it.available_bedrooms,
                it.total_bedrooms,
                it.price,
            )
            dataArray.add(item)
        }
        return flowOf(dataArray)
    }

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

}