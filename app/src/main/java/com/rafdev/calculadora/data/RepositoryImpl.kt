package com.rafdev.calculadora.data

import android.util.Log
import com.rafdev.calculadora.data.local.ButtonService
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val buttonService: ButtonService) : Repository {

    val TAG = "repositoryImpl"
    override fun getDataButton(): List<ResponseDataButton> {
        return try {
            val result = buttonService.getDataButton()
            Log.i(TAG, "RESULT${result}")
            return result
        } catch (e: Exception) {
            Log.i(TAG, "An error has occurred: ${e.message}")
            emptyList()
        }
    }

}