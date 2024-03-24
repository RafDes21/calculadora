package com.rafdev.calculadora.domain

import com.rafdev.calculadora.data.local.response.ButtonProvider
import com.rafdev.calculadora.data.local.response.ResponseDataButton

interface Repository {

    fun getDataButton():List<ResponseDataButton>
}