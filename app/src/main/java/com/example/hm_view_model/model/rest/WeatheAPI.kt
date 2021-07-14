package com.example.hm_view_model.model.rest


import com.example.hm_view_model.model.WeatherDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatheAPI {
    /*@GET("informers")
    fun getWetheAsync(
        //@Header("X-Yandex-API-Key") token: String,
        @Query("lat") x: Double,
        @Query("lon") y: Double
    ): Call<WeatherDTO>*/

    @GET("informers")
    fun getWethe(
        //@Header("X-Yandex-API-Key") token: String,
        @Query("lat") x: Double,
        @Query("lon") y: Double
    ): Response<WeatherDTO>
}