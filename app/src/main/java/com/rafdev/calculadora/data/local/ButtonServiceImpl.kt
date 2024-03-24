package com.rafdev.calculadora.data.local

import com.rafdev.calculadora.data.local.response.ButtonProvider
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import javax.inject.Inject

class ButtonServiceImpl @Inject constructor(private val buttonProvider: ButtonProvider): ButtonService {
    override fun getDataButton(): List<ResponseDataButton> {
        return buttonProvider.buttonData
    }
}