package com.serhat.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("symbol")
    var symbol: String,

    @SerializedName("name")
    var name: String
)