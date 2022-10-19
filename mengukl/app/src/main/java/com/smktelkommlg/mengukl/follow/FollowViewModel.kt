package com.smktelkommlg.mengukl.follow

import androidx.lifecycle.*
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.User
import com.smktelkommlg.cores.domain.usecase.UserUseCase
import com.smktelkommlg.mengukl.util.TypeView


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