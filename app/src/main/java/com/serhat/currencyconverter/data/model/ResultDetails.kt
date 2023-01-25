package com.serhat.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class ResultDetails(
    @SerializedName("from")
    var from: String,

    @SerializedName("to")
    var to: String,

    @SerializedName("amountToConvert")
    var amountToConvert: Double,

    @SerializedName("convertedAmount")
    var convertedAmount: Double
)