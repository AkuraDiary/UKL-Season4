package com.asthiseta.bismillahtest.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.asthiseta.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun detailUsers(username : String) = userUseCase.getDetailUser(username).asLiveData()

}