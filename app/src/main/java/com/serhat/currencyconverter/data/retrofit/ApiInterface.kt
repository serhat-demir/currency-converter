package com.serhat.currencyconverter.data.retrofit

import com.serhat.currencyconverter.data.model.ConvertResult
import com.serhat.currencyconverter.data.model.Currency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("supportedCurrencies")
    fun getSupportedCurrencies(): Call<List<Currency>>

    @GET("convert")
    fun convert(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): Call<ConvertResult>
}