package com.serhat.currencyconverter.di

import com.serhat.currencyconverter.data.repository.CurrencyRepository
import com.serhat.currencyconverter.data.retrofit.ApiClient
import com.serhat.currencyconverter.data.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCurrencyRepository(apiService: ApiInterface) = CurrencyRepository(apiService)

    @Provides
    @Singleton
    fun provideApiService() = ApiClient.getApiService()
}