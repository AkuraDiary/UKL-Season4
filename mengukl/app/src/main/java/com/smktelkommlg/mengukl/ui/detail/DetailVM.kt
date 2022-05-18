package com.smktelkommlg.mengukl.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.smktelkommlg.cores.domain.usecase.ItemUseCase

class DetailVM(private val itemUseCase: ItemUseCase) : ViewModel() {

    fun detailItem(name : String) = itemUseCase.getDetailKos(name).asLiveData()


}