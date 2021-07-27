package com.example.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place:Place){//将Place对象存储到SharePreference文件中
        sharePreferences().edit{
            putString("place",Gson().toJson(place))//Place转变为Json对象储存
        }
    }

    fun getSavePlace():Place{
        val placeJson= sharePreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)//Json解析成字符串返回
    }

    fun isPlaceSaved()= sharePreferences().contains("place")

    private fun sharePreferences()=SunnyWeatherApplication.context.getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}