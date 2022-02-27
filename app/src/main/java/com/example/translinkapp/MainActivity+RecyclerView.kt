package com.example.translinkapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class FavouriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val stopName = view.findViewById<TextView>(R.id.stopRowName)
    private val stopRoutes = view.findViewById<TextView>(R.id.stopRowRoute)
    private val stopCity = view.findViewById<TextView>(R.id.stopRowCity)
    private val stopNo = view.findViewById<TextView>(R.id.stopRowNo)

    private val fav = view.findViewById<ImageView>(R.id.stopFav)

    fun bindView(stop: Stop, context: Context) {
        stopName.text = stop.name
        stopRoutes.text = stop.routes
        stopCity.text = stop.city
        stopNo.text = "#" + stop.stopNo
        fav.setImageResource(getFavImage(stop, context))


        fav.setOnClickListener {
            stop.isFavourite = !stop.isFavourite
            fav.setImageResource(getFavImage(stop, context))
        }
    }

    private fun getFavImage(stop: Stop, context: Context): Int {
        if(stop.isFavourite) {
            toastMessage("Favourited", context)
            addToSharedPref(stop, context)
            return R.drawable.btn_star_big_on_pressed
        }
        toastMessage("Unfavourited", context)
        removeFromSharedPref(stop, context)
        return R.drawable.btn_star_big_off_disable
    }
}

class FavouriteAdapter (private val array: ArrayList<Stop>, val context: Context) :
    RecyclerView.Adapter <FavouriteViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.stop_row, parent, false)

        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bindView(array[position], context)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, EstimatesActivity::class.java)
            intent.putExtra("stop", array[position].stopNo)
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}



