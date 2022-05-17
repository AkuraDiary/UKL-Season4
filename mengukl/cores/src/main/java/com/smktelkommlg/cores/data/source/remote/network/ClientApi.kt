package com.smktelkommlg.cores.data.source.remote.network

import com.smktelkommlg.cores.data.source.remote.response.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientApi {

    @GET("kosts")
    suspend fun searchForItem(
        @Query("q") query: String?
    ): List<ItemResponse>

    @GET("kosts")
    suspend fun searchForItemDetail(
        @Query("q") query: String?
    ): List<ItemResponse>
}