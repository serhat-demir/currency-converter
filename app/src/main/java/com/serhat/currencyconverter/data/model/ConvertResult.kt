package com.serhat.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class ConvertResult(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("validationMessage")
    var validationMessage: List<String>,

    @SerializedName("result")
    var result: ResultDetails
)