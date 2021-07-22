package com.example.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*构建Retrofit实例*/
object ServiceCreator {
    private const val BASE_URL="https://api.caiyunapp.com/"

    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//数据转换为json
        .build()

    fun <T> create(serviceClass: Class<T>):T= retrofit.create(serviceClass)

    inline fun <reified T> create():T= create(T::class.java)
}
