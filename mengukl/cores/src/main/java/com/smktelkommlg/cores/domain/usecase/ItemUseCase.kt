package com.smktelkommlg.cores.domain.usecase

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.Item
import kotlinx.coroutines.flow.Flow


interface ItemUseCase {
    fun getAllKos(query : String?) : Flow<Resource<List<Item>>>

    fun getDetailKos(name : String) : Flow<Resource<Item>>
}