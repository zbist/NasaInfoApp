package com.zbistprod.nasainfoapp.model

import com.google.gson.annotations.SerializedName

data class Apod(
    @SerializedName("hdurl") val hdurl: String = "https://apod.nasa.gov/apod/image/2107/Walk_Milkyway.jpg",
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("title") val title: String,
    @SerializedName("explanation") val explanation: String,
)