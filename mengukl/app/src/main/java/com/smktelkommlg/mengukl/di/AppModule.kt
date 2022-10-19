package com.smktelkommlg.mengukl.di

import com.smktelkommlg.mengukl.ui.detail.DetailVM
import com.smktelkommlg.mengukl.ui.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    factory <ItemUseCase>{ItemInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeVM(get()) }
    viewModel { DetailVM(get()) }
}