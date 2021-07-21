package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R

class PlaceFragment:Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }
    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val bgImageView:ImageView?=activity?.findViewById(R.id.bgImageView)
        super.onActivityCreated(savedInstanceState)
        val layoutManager= LinearLayoutManager(activity)

        val recyclerView=activity?.findViewById<RecyclerView>(R.id.recycleView)
        recyclerView?.layoutManager=layoutManager
        adapter= PlaceAdapter(this,viewModel.placeList)
        recyclerView?.adapter=adapter
        val searchPlaceEdit=activity?.findViewById<EditText>(R.id.searchPlaceEdit)
        searchPlaceEdit?.addTextChangedListener{
            editable->
            val content=editable.toString()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView?.visibility=View.GONE

                bgImageView?.visibility=View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }

        }
        viewModel.placeLiveData.observe(this as LifecycleOwner, Observer {
            result->
            val place=result.getOrNull()
            if (place!=null){
                recyclerView?.visibility=View.VISIBLE
                bgImageView?.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(place)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到相关数据",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })



    }
}