package com.example.hm_view_model.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepo {
    val api: WeatheAPI by lazy {
        val adaptr= Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adaptr.create(WeatheAPI::class.java)
    }
}