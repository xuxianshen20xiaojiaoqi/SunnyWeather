package com.example.sunnyweather.logic.network

import android.util.Log
import com.example.sunnyweather.logic.model.DailyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/*封装网络请求*/
object SunnyWeatherNetwork {
    private val placeService=ServiceCreator.create(PlaceService::class.java)//动态代理对象

    suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await() //调用searchPlaces 方法时直接使用重写的await

    private val weatherService=ServiceCreator.create(WeatherService::class.java)

    suspend fun getDailyWeather(lng:String,lat:String) =weatherService.getDailyWeather(lng,lat).await()



    suspend fun getRealtimeWeather(lng: String,lat: String)= weatherService.getRealtimeWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine {
            continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if (body!=null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}