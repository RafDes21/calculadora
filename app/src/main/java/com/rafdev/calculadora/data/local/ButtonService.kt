package com.rafdev.calculadora.data.local

import com.rafdev.calculadora.data.local.response.ButtonProvider
import com.rafdev.calculadora.data.local.response.ResponseDataButton

interface ButtonService {

    fun getDataButton():List<ResponseDataButton>
}