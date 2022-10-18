package com.smktelkommlg.cores.domain.usecase

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow

class ItemInteractor (private val itemRepos : IItemRepository) : ItemUseCase{
    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
        return itemRepos.getAllKos(query)
    }

    override fun getDetailKos(name: String): Flow<Resource<Item>> {
        return itemRepos.getDetailKos(name)
    }
}