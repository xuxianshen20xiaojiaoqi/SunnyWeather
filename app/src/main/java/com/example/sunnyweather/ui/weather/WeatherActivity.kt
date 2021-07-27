package com.example.sunnyweather.ui.weather

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.Weather
import com.example.sunnyweather.logic.model.getSky
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_AUTO
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity:AppCompatActivity() {

    val viewModel   by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState,)
        val decorView=window.decorView
        decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor=Color.TRANSPARENT
        setContentView(R.layout.activity_weather)
        if (viewModel.locationLng.isEmpty()){
            viewModel.locationLng=intent.getStringExtra("location_lng") ?: ""

        }

        if (viewModel.locationLat.isEmpty()){
            viewModel.locationLat=intent.getStringExtra("location_lat") ?: ""
        }

        if (viewModel.placeName.isEmpty()){
            viewModel.placeName=intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this, Observer {
            result->
            val weather=result.getOrNull()
            if (weather!=null){
                showWeather(weather)
            }else{
                Toast.makeText(this,"无法成功获取天气信息",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)
    }

    private fun showWeather(weather:Weather){
        val placeName:TextView=findViewById(R.id.placeName)
        placeName.text=viewModel.placeName
        val realtime=weather.realtime
        val daily=weather.daily
        //填充now.xml布局中数据
        val currentTempText="${realtime.temperature.toInt()} ℃"
        val currentTemp:TextView=findViewById(R.id.currentTemp)
        currentTemp.text=currentTempText
        val currentSky:TextView=findViewById(R.id.currentSky)
        currentSky.text= getSky(realtime.skycon).info
        val currentPM25Text="空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        val currentAQI:TextView=findViewById(R.id.currentAQI)
        currentAQI.text=currentPM25Text
        val nowLayout:RelativeLayout=findViewById(R.id.nowLayout)
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)

        //forecast.xml数据
        val forecastLayout:LinearLayout=findViewById(R.id.forecastLayout)
        forecastLayout.removeAllViews()
        val days=daily.skycon.size
        for (i in 0 until days){
            val skycon=daily.skycon[i]
            val temperature=daily.temperature[i]
            val view=LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false)
            val dateInfo=view.findViewById(R.id.dateInfo) as  TextView
            val skyIcon=view.findViewById(R.id.skyIcon) as  ImageView
            val skyInfo=view.findViewById<TextView>(R.id.skyInfo)
            val temperatureInfo=view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat=SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text=simpleDateFormat.format(skycon.date)
            val sky= getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text=sky.info
            val tempText="${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text=tempText
            forecastLayout.addView(view)
        }

        //填充life_index.xml布局中的数据
        val lifeIndex=daily.lifeIndex
        val coldRiskText=findViewById<TextView>(R.id.coldRiskText)
        coldRiskText.text=lifeIndex.coldRisk[0].desc
        val dressingText=findViewById<TextView>(R.id.dressingText)
        dressingText.text=lifeIndex.dressing[0].desc
        val ultravioletText=findViewById<TextView>(R.id.ultravioletText)
        ultravioletText.text=lifeIndex.ultraviolet[0].desc
        val carWashText=findViewById<TextView>(R.id.carWashText)
        carWashText.text=lifeIndex.carWashing[0].desc
        val weatherLayout:ScrollView=findViewById(R.id.weatherLayout)
        weatherLayout.visibility=View.VISIBLE


    }
}