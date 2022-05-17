package com.smktelkommlg.cores.data.source.remote

import com.smktelkommlg.cores.data.source.remote.network.ApiResponse
import com.smktelkommlg.cores.data.source.remote.network.ClientApi
import com.smktelkommlg.cores.data.source.remote.response.ItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val clientApi: ClientApi) {

    suspend fun getAllItem(query : String?) : Flow<ApiResponse<List<ItemResponse>>> =
        flow {
            try {
                val userSearch = clientApi.searchForItem(query)
                if(userSearch.isEmpty())
                    emit(ApiResponse.IsError("No data found"))
                else
                    emit(ApiResponse.IsSuccess(userSearch))
            }catch (e : Exception){
                emit(ApiResponse.IsError(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getItemDetail(name : String) : Flow<ApiResponse<ItemResponse>> =
        flow {
            try {
                val userSearch = clientApi.searchForItemDetail(name)
                emit(ApiResponse.IsSuccess(userSearch))
            }catch (e : Exception){
                emit(ApiResponse.IsError(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}