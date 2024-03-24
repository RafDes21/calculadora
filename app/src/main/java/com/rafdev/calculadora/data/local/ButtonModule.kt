package com.rafdev.calculadora.data.local

import com.rafdev.calculadora.data.RepositoryImpl
import com.rafdev.calculadora.data.local.response.ButtonProvider
import com.rafdev.calculadora.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ButtonModule {

    @Singleton
    @Provides
    fun provideButtonProvider(): ButtonProvider = ButtonProvider()

    @Singleton
    @Provides
    fun provideButtonService(buttonProvider: ButtonProvider): ButtonService = ButtonServiceImpl(buttonProvider)

    @Singleton
    @Provides
    fun provideRepository(buttonService: ButtonService): Repository = RepositoryImpl(buttonService)

}