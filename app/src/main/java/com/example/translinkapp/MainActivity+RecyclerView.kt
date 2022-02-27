package com.example.translinkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FavouriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val stopName = view.findViewById<TextView>(R.id.stopRowName)
    private val stopRoutes = view.findViewById<TextView>(R.id.stopRowRoute)
    private val stopCity = view.findViewById<TextView>(R.id.stopRowCity)
    private val stopNo = view.findViewById<TextView>(R.id.stopRowNo)

    fun bindView(stop: Stop) {
        stopName.text = stop.name
        stopRoutes.text = stop.routes
        stopCity.text = stop.city
        stopNo.text = "#" + stop.stopNo
    }
}

class FavouriteAdapter (private val array: ArrayList<Stop>) :
    RecyclerView.Adapter <FavouriteViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.stop_row, parent, false)

        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        return holder.bindView(array[position])
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

