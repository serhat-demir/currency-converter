package com.serhat.currencyconverter.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.serhat.currencyconverter.R
import com.serhat.currencyconverter.databinding.ActivityMainBinding
import com.serhat.currencyconverter.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityMain = this

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        viewModel.currencies.observe(this) {
            it?.let {
                binding.spSourceCurrency.setItems(it.map { it1 -> it1.symbol })
                binding.spTargetCurrency.setItems(it.map { it1 -> it1.symbol })
            }
        }

        viewModel.convertedAmount.observe(this) {
            it?.let {
                binding.txtTargetCurrency.setText(it.toString())
            }
        }

        viewModel.getSupportedCurrencies()
    }

    fun convert(amountStr: String, sourceCurrencyIndex: Int, targetCurrencyIndex: Int) {
        when {
            sourceCurrencyIndex == -1 -> {
                Toast.makeText(this@MainActivity, getString(R.string.msg_source_currency), Toast.LENGTH_SHORT).show()
                return
            }

            targetCurrencyIndex == -1 -> {
                Toast.makeText(this@MainActivity, getString(R.string.msg_target_currency), Toast.LENGTH_SHORT).show()
                return
            }

            sourceCurrencyIndex == targetCurrencyIndex -> {
                Toast.makeText(this@MainActivity, getString(R.string.msg_same_currency), Toast.LENGTH_SHORT).show()
                return
            }
        }

        val amount: Double? = amountStr.toDoubleOrNull()
        amount?.let {
            val from = viewModel.currencies.value!![sourceCurrencyIndex].symbol
            val to = viewModel.currencies.value!![targetCurrencyIndex].symbol

            viewModel.convert(from, to, amount)
        }
    }
}