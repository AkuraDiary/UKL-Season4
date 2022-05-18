package com.smktelkommlg.mengukl

import android.app.Application
import com.smktelkommlg.cores.di.networkModule
import com.smktelkommlg.cores.di.repositoryModule
import com.smktelkommlg.mengukl.di.useCaseModule
import com.smktelkommlg.mengukl.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyAplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}