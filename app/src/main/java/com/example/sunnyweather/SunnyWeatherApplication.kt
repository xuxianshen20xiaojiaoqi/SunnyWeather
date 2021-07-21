package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication:Application() {
    @SuppressLint("StaticFieldLeak")
    companion object{
        lateinit var context: Context
        const val TOKEN="https://dashboard.caiyunapp.com/v1/token/60f14a4ce5aa8300702bf119/?type=1"
    }

    override fun onCreate() {//全局获取context
        super.onCreate()
        context=applicationContext
    }
}