package com.smktelkommlg.mengukl.galeri

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User
import com.smktelkommlg.cores.domain.usecase.UserUseCase


class GalleryViewModel(userUseCase : UserUseCase) : ViewModel()  {

    private var username : MutableLiveData<String> = MutableLiveData()

    val users : LiveData<Resource<List<User>>> = userUseCase.getAllUsers().asLiveData()
}