package com.smktelkommlg.mengukl.ui.home

import androidx.lifecycle.*
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.cores.domain.model.Item
import com.smktelkommlg.cores.domain.usecase.ItemUseCase

class HomeVM(itemUseCase : ItemUseCase) : ViewModel(){
    private var name : MutableLiveData<String> = MutableLiveData()

    fun setForSearch(query: String?){
        if(name.value == query){
            return
        }
        name.value = query!!
    }

    val item : LiveData<Resource<List<Item>>> = Transformations
        .switchMap(name){
            itemUseCase.getAllKos(it).asLiveData()
        }

}