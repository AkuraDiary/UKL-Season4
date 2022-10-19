package com.asthiseta.bismillahtest.galeri

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.User
import com.asthiseta.core.domain.usecase.UserUseCase

class GalleryViewModel(userUseCase : UserUseCase) : ViewModel()  {

    private var username : MutableLiveData<String> = MutableLiveData()

    val users : LiveData<Resource<List<User>>> = userUseCase.getAllUsers().asLiveData()
}