package com.example.translinkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EstimatesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimates)

        val stop = intent.getStringExtra("stop").toString()

        val myEstimates = callStopEstimates(stop)

        findViewById<TextView>(R.id.estimateStopNo).text = stop

        val nextBusRv = findViewById<RecyclerView>(R.id.nextBusRV)
        nextBusRv.layoutManager = LinearLayoutManager(this)
        nextBusRv.adapter = EstimatesAdapter(myEstimates)
    }
}


