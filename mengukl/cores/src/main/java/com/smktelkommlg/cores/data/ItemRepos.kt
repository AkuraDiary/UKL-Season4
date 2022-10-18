package com.smktelkommlg.cores.data

//class ItemRepos (
//    private val remoteDataSource : RemoteDataSource,
//    ) : IItemRepository{
//    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
//        return object : NetworkResources<List<Item>, List<ItemResponse>>(){
//            override fun loadFromNetwork(data: List<ItemResponse>): Flow<List<Item>> {
//                return DataMapper.mapResponsesToDomain(data)
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<ItemResponse>>> {
//                return remoteDataSource.getAllItem(query)
//            }
//        }.asFlow()
//    }
//
//    override fun getDetailKos(name: String): Flow<Resource<Item>> {
//        return object : NetworkResources<Item, ItemResponse>() {
//            override fun loadFromNetwork(data: List<ItemResponse>): Flow<Item> {
//                return DataMapper.mapResponseToDomain(data)
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<ItemResponse>>> {
//                return remoteDataSource.getItemDetail(name)
//            }
//        }.asFlow()
//    }
//
//}