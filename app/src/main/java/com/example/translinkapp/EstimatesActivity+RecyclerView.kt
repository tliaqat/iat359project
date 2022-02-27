package com.example.translinkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EstimatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nextBusTitle = itemView.findViewById<TextView>(R.id.nextBusTitle)
    private val nextBusDirection = itemView.findViewById<TextView>(R.id.nextBusDirection)

    fun bindView(nextBus: NextBus, hash: HashMap<NextBus, ArrayList<StopEstimate>>) {
        nextBusTitle.text = "${nextBus.routeNo} ${nextBus.routeName}"
        nextBusDirection.text = nextBus.direction.toString()

        val timesRv = itemView.findViewById<RecyclerView>(R.id.timesRV)
        timesRv.layoutManager = LinearLayoutManager(itemView.context)
        timesRv.adapter = TimesAdapter(hash[nextBus]!!)
    }
}

class EstimatesAdapter (private val hashMap: HashMap<NextBus, ArrayList<StopEstimate>>) :
    RecyclerView.Adapter <EstimatesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimatesViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.next_bus_section, parent, false)

        return EstimatesViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstimatesViewHolder, position: Int) {
        return holder.bindView(hashMap.keys.toList().get(position), hashMap)
    }

    override fun getItemCount(): Int {
        return hashMap.size
    }
}