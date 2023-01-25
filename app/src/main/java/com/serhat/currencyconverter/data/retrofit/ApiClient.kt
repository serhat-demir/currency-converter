package com.serhat.currencyconverter.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://currency-converter18.p.rapidapi.com/api/v1/"

        fun getApiService(): ApiInterface {
            val client = OkHttpClient.Builder()
            client.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-RapidAPI-Key", "PUT_YOUR_API_KEY_HERE")
                    .addHeader("X-RapidAPI-Host", "currency-converter18.p.rapidapi.com")
                    .build()

                chain.proceed(request)
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}