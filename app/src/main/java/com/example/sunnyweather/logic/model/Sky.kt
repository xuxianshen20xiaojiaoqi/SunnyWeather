package com.example.sunnyweather.logic.model

import com.example.sunnyweather.R


class Sky (val info:String,val icon:Int,val bg:Int)

private val sky= mapOf(
    "CLEAR_DAY" to Sky("晴", R.drawable.ic_clear_day,R.drawable.bg_clear_day),
    "CLEAR_NIGHT" to Sky("晴",R.drawable.ic_clear_night,R.drawable.bg_clear_night),
    "PARTLY_CLOUDY_DAY" to Sky(" 多 云", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day),
    "PARTLY_CLOUDY_NIGHT" to Sky(" 多 云", R.drawable.ic_partly_cloud_night, R.drawable.bg_partly_cloudy_night),
    "CLOUDY" to Sky(" 阴", R.drawable.ic_cloudy, R.drawable.bg_cloudy),
    "WIND" to Sky(" 大 风", R.drawable.ic_cloudy, R.drawable.bg_wind),
    "LIGHT_RAIN" to Sky(" 小 雨", R.drawable.ic_light_rain, R.drawable.bg_rain),
    "MODERATE_RAIN" to Sky(" 中 雨", R.drawable.ic_moderate_rain, R.drawable.bg_rain),
    "HEAVY_RAIN" to Sky(" 大 雨", R.drawable.ic_heavy_rain, R.drawable.bg_rain),
    "STORM_RAIN" to Sky(" 暴 雨", R.drawable.ic_storm_rain, R.drawable.bg_rain),
    "THUNDER_SHOWER" to Sky(" 雷 阵 雨", R.drawable.ic_thunder_shower, R.drawable.bg_rain),
    "SLEET" to Sky(" 雨 夹 雪", R.drawable.ic_sleet, R.drawable.bg_rain),
    "LIGHT_SNOW" to Sky(" 小 雪", R.drawable.ic_light_snow, R.drawable.bg_snow),
    "MODERATE_SNOW" to Sky(" 中 雪", R.drawable.ic_moderate_snow, R.drawable.bg_snow),
    "HEAVY_SNOW" to Sky(" 大 雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    "STORM_SNOW" to Sky(" 暴 雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    "HAIL" to Sky(" 冰 雹", R.drawable.ic_hail, R.drawable.bg_snow),
    "LIGHT_HAZE" to Sky(" 轻 度 雾 霾", R.drawable.ic_light_haze, R.drawable.bg_fog),
    "MODERATE_HAZE" to Sky(" 中 度 雾 霾", R.drawable.ic_moderate_haze, R.drawable.bg_fog),
    "HEAVY_HAZE" to Sky(" 重 度 雾 霾", R.drawable.ic_heavy_haze, R.drawable.bg_fog),
    "FOG" to Sky(" 雾", R.drawable.ic_fog, R.drawable.bg_fog),
    "DUST" to Sky(" 浮 尘", R.drawable.ic_fog, R.drawable.bg_fog)
)

fun getSky(skycon:String):Sky{
    return sky[skycon] ?: sky["CLEAR_DAY"]!!
}
