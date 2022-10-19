package com.asthiseta.bismillahtest.follow

import androidx.lifecycle.*
import com.asthiseta.bismillahtest.util.TypeView
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.User
import com.asthiseta.core.domain.usecase.UserUseCase

class FollowViewModel(userUseCase: UserUseCase) : ViewModel(){
    private var username : MutableLiveData<String> = MutableLiveData()
    private lateinit var typeView : TypeView

    fun setFollow(user : String, type: TypeView){
        if(username.value == user){
            return
        }
        username.value = user
        typeView = type
    }

    val favoriteUsers: LiveData<Resource<List<User>>> = Transformations
        .switchMap(username) {
            when (typeView) {
                TypeView.FOLLOWER -> userUseCase.getAllFollowers(it).asLiveData()
                TypeView.FOLLOWING -> userUseCase.getAllFollowing(it).asLiveData()
            }
        }
}