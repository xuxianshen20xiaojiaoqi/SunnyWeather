package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceReaponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh-CN")
    fun searchPlaces(@Query("query") query: String):Call<PlaceReaponse>

}