package com.serhat.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serhat.currencyconverter.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cRepo: CurrencyRepository): ViewModel() {
    val currencies = cRepo.currencies
    val convertedAmount = cRepo.convertedAmount

    fun getSupportedCurrencies() {
        cRepo.getSupportedCurrencies()
    }

    fun convert(from: String, to: String, amount: Double) {
        cRepo.convert(from, to, amount)
    }
}