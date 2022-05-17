package com.smktelkommlg.cores.data.source

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.data.source.remote.network.ApiResponse
import com.smktelkommlg.cores.data.source.remote.response.ItemResponse
import kotlinx.coroutines.flow.*


abstract class NetworkResources<ResultType, RequestType> {
    private val result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        when(val apiResponse = createCall().first()){
            is ApiResponse.IsSuccess -> {
                emitAll(loadFromNetwork(apiResponse.data).map{
                    Resource.Success(it)
                })
            }

            is ApiResponse.IsError -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
    protected abstract suspend fun createCall(): Flow<ApiResponse<List<ItemResponse>>>
    protected abstract fun loadFromNetwork(data: List<ItemResponse>) : Flow<ResultType>
}