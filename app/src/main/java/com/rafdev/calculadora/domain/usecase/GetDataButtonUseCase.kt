package com.rafdev.calculadora.domain.usecase

import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.domain.Repository
import javax.inject.Inject

class GetDataButtonUseCase @Inject constructor(private val repository: Repository) {

  operator fun invoke():List<ResponseDataButton>{
      return repository.getDataButton()
  }
}