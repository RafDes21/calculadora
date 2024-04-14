package com.rafdev.calculadora.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.usecase.GetDataButtonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _displayValue = MutableLiveData<List<String>>()
    val displayValue: LiveData<List<String>> = _displayValue

    private val _resultData = MutableLiveData<String>()
    val resultData: LiveData<String> = _resultData


    init {
        fetchDataButton()
    }

    private fun fetchDataButton() {
        val result = getDataButtonUseCase()
        _dataButton.value = result
    }

    fun onButtonClicked(value: String) {

        if (mResultCalculator == ResultCalculator.ERROR) {
            _resultData.value = "0"
            mResultCalculator = null
        }
        if (value == "C") {
            return clearData()
        }
        if (value == "=") {
            return resultData()
        }

        val currentDisplay = displayValue.value ?: emptyList()

        if (value.matches("[0-9.]".toRegex())) {
            val lastElement = currentDisplay.lastOrNull()
            if (lastElement != null && lastElement.matches("[0-9.]+".toRegex())) {
                _displayValue.value = currentDisplay.dropLast(1) + (lastElement + value)
            } else {
                _displayValue.value = currentDisplay + value
            }
        } else {
            _displayValue.value = currentDisplay + value
        }
    }

    private fun clearData() {
        _displayValue.value = emptyList()
        if (mResultCalculator == ResultCalculator.ERROR) {
            _resultData.value = "0"
            mResultCalculator = null
        }
    }

    private fun resultData() {
        _displayValue.value?.let { performCalculation(it) }
    }

    private fun performCalculation(input: List<String>) {
        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<String>()

        try {
            // Iterar sobre la lista de entrada para separar los números y los operadores
            for (item in input) {
                // Si es un número, agregarlo a la lista de números
                if (item.toDoubleOrNull() != null) {
                    numbers.add(item.toDouble())
                } else {
                    // Si es un operador, agregarlo a la lista de operadores
                    operators.add(item)
                }
            }

            // Iterar sobre la lista de operadores para realizar multiplicaciones y divisiones primero
            var i = 0
            while (i < operators.size) {
                val operator = operators[i]
                if (operator == "*" || operator == "/") {
                    val num1 = numbers[i]
                    val num2 = numbers[i + 1]
                    val result = if (operator == "*") num1 * num2 else num1 / num2
                    // Remover el primer número y el operador
                    numbers.removeAt(i)
                    // Remover el segundo número
                    numbers[i] = result
                    // Remover el operador
                    operators.removeAt(i)
                } else {
                    // Si no es una multiplicación o división, avanzar al siguiente operador
                    i++
                }
            }

            // Iterar sobre la lista de operadores restante para realizar sumas y restas
            var result = numbers[0]
            for (i in 0 until operators.size) {
                val operator = operators[i]
                val nextNumber = numbers[i + 1]
                if (operator == "+") {
                    result += nextNumber
                } else if (operator == "-") {
                    result -= nextNumber
                }
            }

            // Actualizar el LiveData con el resultado como String
            _resultData.value = result.toString()
        } catch (e: Exception) {
            mResultCalculator = ResultCalculator.ERROR
            // Enviar un mensaje de error en caso de excepción
            _resultData.value = "Error: ${e.message}"
            _displayValue.value = emptyList()
            Log.e("Error", "Error during calculation", e)
        }
    }
}


