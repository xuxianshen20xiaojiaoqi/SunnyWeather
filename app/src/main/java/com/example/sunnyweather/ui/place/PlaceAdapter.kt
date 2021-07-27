package com.example.sunnyweather.ui.place

import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.model.Weather
import com.example.sunnyweather.ui.weather.WeatherActivity
import java.util.*

/*Adapt适配器*/
class PlaceAdapter(private val fragment:PlaceFragment,private val placeList: List<Place>): RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val placeName:TextView=view.findViewById(R.id.placeName)
        val placeAddress:TextView=view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        val holder=ViewHolder(view)
            holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val place=placeList[position]
            val activity=fragment.activity
            if (activity is WeatherActivity){
                val drawerLayout=activity.findViewById<DrawerLayout>(R.id.drawerLayout)
                drawerLayout.closeDrawers()
                activity.viewModel.locationLat=place.location.lat
                activity.viewModel.locationLng=place.location.lng
                activity.viewModel.placeName=place.name
                activity.refreshWeather()
            }else{
                val intent=Intent(parent.context,WeatherActivity::class.java).apply {
                    putExtra("location_lat",place.location.lat)
                    putExtra("location_lng",place.location.lng)
                    putExtra("place_name",place.name)
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
                fragment.viewModel.savePlace(place)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place=placeList[position]
        holder.placeAddress.text=place.address
        holder.placeName.text=place.name
    }

    override fun getItemCount()=placeList.size
}

