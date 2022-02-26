package com.example.translinkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var myStops = ArrayList<Stop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callStopEstimates("59316")

        myStops.add(callStop("59316"))
        myStops.add(callStop("52851"))
        myStops.add(callStop("60980"))

        Log.i("TAG2", " Main stops = " + myStops[0].stopNo.toString())

        val favRv = findViewById<RecyclerView>(R.id.favRecyclerView)
        favRv.layoutManager = LinearLayoutManager(this)
        favRv.adapter = FavouriteAdapter(myStops)


    }
}

