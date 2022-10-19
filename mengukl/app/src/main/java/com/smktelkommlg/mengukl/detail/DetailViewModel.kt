package com.smktelkommlg.mengukl.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.smktelkommlg.cores.domain.usecase.UserUseCase


class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun detailUsers(username : String) = userUseCase.getDetailUser(username).asLiveData()

}