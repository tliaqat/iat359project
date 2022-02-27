package com.example.translinkapp

import android.content.Context
import android.util.Log
import okhttp3.*
import java.io.IOException

private val client = OkHttpClient()
private val base = "https://api.translink.ca/rttiapi/v1/stops/"
private val key = "?apikey=7E396B1FZwGjLKFn6mbd"

fun callStop(stop: String, context: Context): Stop {
    val url = base + stop + key

    val request = Request.Builder()
        .url(url)
        .build()

    var stop1 = Stop()
    var returned = false
    var failed = false
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {failed = true}
        override fun onResponse(call: Call, response: Response) {
            stop1 = parseStop(response.body()?.string().toString(), context)
            returned = true
        }
    })
    if(failed) {
        return Stop()
    }
    while(!returned) {
        Log.i("TAG", "Busywait")
    }
    return stop1
}

fun callStopEstimates(stop: String): HashMap<NextBus, ArrayList<StopEstimate>> {
    val url = base + stop + "/estimates" + key

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
        Log.i("TAG", "Busywait")
    }
    return hash
}