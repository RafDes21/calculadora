package com.rafdev.calculadora.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.usecase.GetDataButtonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val getDataButtonUseCase: GetDataButtonUseCase) :
    ViewModel() {

    private var mResultCalculator: ResultCalculator? = null

    enum class ResultCalculator {
        ERROR
    }

    private val _dataButton = MutableLiveData<List<ResponseDataButton>>()
    val dataButton: LiveData<List<ResponseDataButton>> = _dataButton

//    private val _displayValue = MutableLiveData<List<String>>()
//    val displayValue: LiveData<List<String>> = _displayValue
//
//    private val _resultData = MutableLiveData<String>()
//    val resultData: LiveData<String> = _resultData


    init {
        fetchDataButton()
    }

    private fun fetchDataButton() {
        val result = getDataButtonUseCase()
        _dataButton.value = result
    }

    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String> = _expression

    private val _result = MutableLiveData<Double?>()
    val result: MutableLiveData<Double?> = _result

    fun appendToExpression(text: String) {
        if (text == "C") {
            clearExpression()
        }
        _expression.value = (_expression.value ?: "") + text
    }

    private fun clearExpression() {
        _expression.value = ""
    }

    fun calculateResult() {
        try {
            val expressionValue = _expression.value ?: return
            val resultValue = ExpressionBuilder(expressionValue).build().evaluate()
            _result.value = resultValue
        } catch (e: Exception) {
            _result.value = null
        }
    }
}


