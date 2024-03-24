package com.rafdev.calculadora

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.usecase.GetDataButtonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val getDataButtonUseCase: GetDataButtonUseCase): ViewModel() {

    val dataButton = MutableLiveData<List<ResponseDataButton>>()

    init {
        fetchDataButton()
    }
    fun fetchDataButton(){
        val result = getDataButtonUseCase()
        dataButton.value = result
    }

}