package com.openapi.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class MovieCritics(
    @SerializedName("display_title")
    val title: String,
    @SerializedName("summary_short")
    val summaryShort: String,
    @SerializedName("mpaa_rating")
    val rating_MPAA: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("publication_date")
    val publication_date: String,
    @SerializedName("multimedia")
    val multimedia: Multimedia?
    )

