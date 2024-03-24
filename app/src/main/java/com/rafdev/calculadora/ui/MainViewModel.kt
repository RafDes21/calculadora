package com.rafdev.calculadora.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.usecase.GetDataButtonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val getDataButtonUseCase: GetDataButtonUseCase): ViewModel() {

    private val _dataButton = MutableLiveData<List<ResponseDataButton>>()
    val dataButton: LiveData<List<ResponseDataButton>> = _dataButton

    init {
        fetchDataButton()
    }

    private fun fetchDataButton(){
        val result = getDataButtonUseCase()
        _dataButton.value = result
    }
}