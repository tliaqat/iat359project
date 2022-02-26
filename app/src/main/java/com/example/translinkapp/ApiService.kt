package com.example.translinkapp

import android.util.Log
import okhttp3.*
import java.io.IOException

private val client = OkHttpClient()
private val base = "https://api.translink.ca/rttiapi/v1/"
private val key = "?apikey=7E396B1FZwGjLKFn6mbd"

fun callStop(stop: String): Stop {
    val url = base + "stops/" + stop + key

    val request = Request.Builder()
        .url(url)
        .build()

    var stop1 = Stop()
    var returned = false
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            stop1 = parseStop(response.body()?.string().toString())!!
            Log.i("TAG", "returned1 = " + returned.toString())
            returned = true
            Log.i("TAG", "returned2 = " + returned.toString())
        }
    })
    while(!returned) {
        Log.i("TAG", "returned3 = " + returned.toString())
    }
    return stop1
}

fun callStopEstimates(stop: String): HashMap<NextBus, ArrayList<StopEstimate>> {
    val url = base + "stops/" + stop + "/estimates" + key

    val request = Request.Builder()
        .url(url)
        .build()
    var hash = HashMap<NextBus, ArrayList<StopEstimate>>()
    var returned = false
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            hash = parseEstimate(response.body()?.string().toString())
            returned = true
        }
    })
    while(!returned) {
        Log.i("TAG", "returned3 = " + returned.toString())
    }
    return hash
}