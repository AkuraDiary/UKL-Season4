package com.smktelkommlg.mengukl.home

import androidx.lifecycle.*

import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User
import com.smktelkommlg.cores.domain.usecase.UserUseCase


class HomeViewModel(userUseCase : UserUseCase) : ViewModel()  {

    val users : LiveData<Resource<List<User>>> = userUseCase.getAllUsers().asLiveData()
}