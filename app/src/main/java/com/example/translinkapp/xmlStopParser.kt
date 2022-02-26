package com.example.translinkapp

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

fun parseStop(xmlData: String) {
    val pullParserFactory: XmlPullParserFactory
    try {
        pullParserFactory = XmlPullParserFactory.newInstance()
        val parser = pullParserFactory.newPullParser()
        val inputStream = xmlData.byteInputStream()

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)

        val stops = parseXmlStop(parser)
        var text = ""

        for(stop in stops!!) {
            text += "name: " + stop.routes
        }
        println("THIS IS WHO WE ARE " + stops[0].city)
    } catch (e: XmlPullParserException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@Throws(XmlPullParserException::class, IOException::class)
fun parseXmlStop(parser: XmlPullParser) : ArrayList<Stop>? {
    var stops: ArrayList<Stop>? = null
    var eventtype = parser.eventType
    var stop: Stop? = null

    while (eventtype != XmlPullParser.END_DOCUMENT) {
        val name: String
        when(eventtype) {
            XmlPullParser.START_DOCUMENT -> stops = ArrayList()
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
                name = parser.name
                if(name.equals("Stop", ignoreCase = true) && stop != null) {
                    stops!!.add(stop)
                }
            }
        }
        eventtype = parser.next()
    }
    return stops
}



