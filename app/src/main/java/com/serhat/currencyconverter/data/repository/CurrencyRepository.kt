package com.serhat.currencyconverter.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.serhat.currencyconverter.data.model.ConvertResult
import com.serhat.currencyconverter.data.model.Currency
import com.serhat.currencyconverter.data.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRepository(private val apiService: ApiInterface) {
    val currencies = MutableLiveData<List<Currency>>()
    val convertedAmount = MutableLiveData<Double>()

    fun getSupportedCurrencies() {
        apiService.getSupportedCurrencies().enqueue(object: Callback<List<Currency>> {
            override fun onResponse(call: Call<List<Currency>>, response: Response<List<Currency>>) {
                currencies.value = response.body()
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                t.message?.let { Log.e("sa", it) }
            }
        })
    }

    fun convert(from: String, to: String, amount: Double) {
        apiService.convert(from, to, amount).enqueue(object: Callback<ConvertResult> {
            override fun onResponse(call: Call<ConvertResult>, response: Response<ConvertResult>) {
                convertedAmount.value = response.body()?.result?.convertedAmount
            }

            override fun onFailure(call: Call<ConvertResult>, t: Throwable) {
                t.message?.let { Log.e("sa", it) }
            }
        })
    }
}