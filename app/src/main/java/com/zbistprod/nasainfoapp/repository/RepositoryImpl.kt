package com.zbistprod.nasainfoapp.repository

import com.zbistprod.nasainfoapp.BuildConfig
import com.zbistprod.nasainfoapp.model.Apod
import com.zbistprod.nasainfoapp.model.network.ApodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryImpl : IRepository {

    private val nasaApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApodApi::class.java)

    override fun getApod(callback: (result: RepositoryResult<Apod>) -> Unit) {
        nasaApi.getApod(BuildConfig.NASA_API_KEY).enqueue(object : Callback<Apod> {
            override fun onResponse(call: Call<Apod>, response: Response<Apod>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.invoke(RepositoryResult.Success(response.body()!!))
                } else {
                    callback.invoke(RepositoryResult.Error(Throwable(message = "Some Error")))
                }
            }

            override fun onFailure(call: Call<Apod>, t: Throwable) {
                callback.invoke(RepositoryResult.Error(t))
            }

        })

    }

    override fun getNotes() = mutableListOf(
        "some text",
        "some text some text",
        "some text some text some text",
        "some text some text some text some text",
        "some text some text some text some text some text some text some text some text",
        "some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text",
        "some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text",
    )
}