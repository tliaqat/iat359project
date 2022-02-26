package com.example.translinkapp

import okhttp3.*
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import java.io.IOException

private val client = OkHttpClient()
private val base = "https://api.translink.ca/rttiapi/v1/"
private val key = "?apikey=7E396B1FZwGjLKFn6mbd"

fun callStop(stop: String) {
    val url = base + "stops/" + stop + key

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            parseStop(response.body()?.string().toString())
        }
    })

}

fun callStopEstimates(stop: String) {
    val url = base + "stops/" + stop + "/estimates" + key

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
    })
}