package com.smktelkommlg.cores.domain.repository

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface IItemRepository {
    fun getAllKos(query: String?): Flow<Resource<List<Item>>>

    fun getDetailKos(name : String): Flow<Resource<Item>>
}