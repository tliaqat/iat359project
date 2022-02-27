package com.example.translinkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val timesDestination = itemView.findViewById<TextView>(R.id.timeDestination)
    private val timesExpectedLeave = itemView.findViewById<TextView>(R.id.timeLeaveTime)
    private val timesCountdown = itemView.findViewById<TextView>(R.id.timeCountdown)
    private val timesLastUpdated = itemView.findViewById<TextView>(R.id.timeUpdated)

    fun bindView(time: StopEstimate) {
        timesDestination.text = time.destination
        timesExpectedLeave.text = "Arrives: " + time.expectedLeaveTime.toString().take(7)
        timesCountdown.text = time.expectedCountdown + "min"
        timesLastUpdated.text = "Last Updated: " + time.lastUpdate
    }
}

class TimesAdapter (private val array: ArrayList<StopEstimate>) :
    RecyclerView.Adapter <TimesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.times_row, parent, false)

        return TimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimesViewHolder, position: Int) {
        return holder.bindView(array[position])
    }

    override fun getItemCount(): Int {
        return array.size
    }
}