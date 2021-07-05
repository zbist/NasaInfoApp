package com.zbistprod.nasainfoapp.model.network

import androidx.viewbinding.BuildConfig
import com.zbistprod.nasainfoapp.model.Apod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {

    @GET("planetary/apod")
    fun getApod(
        @Query("api_key") apiKey: String
    ): Call<Apod>
}