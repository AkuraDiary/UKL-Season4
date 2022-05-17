package com.smktelkommlg.cores.di

import com.smktelkommlg.cores.BuildConfig
import com.smktelkommlg.cores.data.ItemRepos
import com.smktelkommlg.cores.data.source.remote.RemoteDataSource
import com.smktelkommlg.cores.data.source.remote.network.ClientApi
import com.smktelkommlg.cores.domain.repository.IItemRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = BuildConfig.BASE_URL

val networkModule = module{
    single{
        OkHttpClient.Builder()
            .addInterceptor{
                val original = it.request()
                val request = original.newBuilder().build()
                it.proceed(request)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ClientApi::class.java)
    }
}

val repositoryModule = module{
    single { RemoteDataSource(get()) }
    single<IItemRepository>{
        ItemRepos(
            get()
        )
    }
}

