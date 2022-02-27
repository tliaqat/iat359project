package com.example.translinkapp

import android.content.Context
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

fun parseStop(xmlData: String, context: Context): Stop {
    val pullParserFactory: XmlPullParserFactory
    try {
        pullParserFactory = XmlPullParserFactory.newInstance()
        val parser = pullParserFactory.newPullParser()
        val inputStream = xmlData.byteInputStream()

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)

        val stop = parseXmlStop(parser)
        stop!!.isFavourite = isFavouriteStop(stop.stopNo, context)
        return stop
    } catch (e: XmlPullParserException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return Stop()
}

fun isFavouriteStop(stopNo: String?, context: Context): Boolean {
    val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
    val x = sharedPreference.getStringSet("favs", mutableSetOf())
    if (x != null) {
        for (stop in x) {
            if (stopNo == stop) {
                return true
            }
        }
    }
    return false
}

@Throws(XmlPullParserException::class, IOException::class)
fun parseXmlStop(parser: XmlPullParser) : Stop? {
    var eventtype = parser.eventType
    var stop: Stop? = null

    while (eventtype != XmlPullParser.END_DOCUMENT) {
        val name: String
        when(eventtype) {
            XmlPullParser.START_DOCUMENT -> stop = Stop()
            XmlPullParser.START_TAG -> {
                name = parser.name
                if(name == "Stop") {
                    stop = Stop()
                } else if(stop != null) {
                    if(name == "Name") {
                        stop.name = parser.nextText()
                    } else if(name =="StopNo") {
                        stop.stopNo = parser.nextText()
                    } else if(name == "City") {
                        stop.city = parser.nextText()
                    } else if(name == "Routes") {
                        stop.routes = parser.nextText()
                    }
                }
            }
            XmlPullParser.END_TAG -> {
            }
        }
        eventtype = parser.next()
    }
    return stop
}



