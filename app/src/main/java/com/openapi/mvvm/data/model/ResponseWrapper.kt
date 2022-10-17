package com.openapi.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("status")
    val status: String,
    @SerializedName("results")
    val movieCriticsList: List<MovieCritics>
)