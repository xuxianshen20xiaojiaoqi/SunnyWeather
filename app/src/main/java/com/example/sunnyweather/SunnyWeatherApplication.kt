package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication:Application() {
    @SuppressLint("StaticFieldLeak")
    companion object{
        lateinit var context: Context
        const val TOKEN="tvBG4WMnYN4zl7QB"//数据源
    }

    override fun onCreate() {//全局获取context
        super.onCreate()
        context=applicationContext
    }
}