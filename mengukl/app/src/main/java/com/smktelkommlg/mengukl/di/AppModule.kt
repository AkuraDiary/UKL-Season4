package com.smktelkommlg.mengukl.di

import com.asthiseta.bismillahtest.detail.DetailViewModel
import com.asthiseta.bismillahtest.follow.FollowViewModel
import com.asthiseta.bismillahtest.galeri.GalleryViewModel
import com.smktelkommlg.mengukl.home.HomeViewModel
import com.smktelkommlg.cores.domain.usecase.UserInteractor
import com.smktelkommlg.cores.domain.usecase.UserUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val useCaseModule = module{
//    factory <ItemUseCase>{ItemInteractor(get())}
//}
//
//val viewModelModule = module {
//    viewModel { HomeVM(get()) }
//    viewModel { DetailVM(get()) }
//}

val useCaseModule = module{
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }
    viewModel { FollowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
}