package com.example.translinkapp

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

fun parseEstimate(xmlData: String): HashMap<NextBus, ArrayList<StopEstimate>> {
    val pullParserFactory: XmlPullParserFactory
    try {
        pullParserFactory = XmlPullParserFactory.newInstance()
        val parser = pullParserFactory.newPullParser()
        val inputStream = xmlData.byteInputStream()

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)

        val stops = parseXmlEstimate(parser)
        return stops
    } catch (e: XmlPullParserException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return HashMap()
}

@Throws(XmlPullParserException::class, IOException::class)
fun parseXmlEstimate(parser: XmlPullParser) : HashMap<NextBus, ArrayList<StopEstimate>> {
    var stops: ArrayList<StopEstimate>? = null
    var eventtype = parser.eventType
    var stopEstimate: StopEstimate? = null
    var nextBus: NextBus? = null
    val hash: HashMap<NextBus, ArrayList<StopEstimate>> = HashMap()


    while (eventtype != XmlPullParser.END_DOCUMENT) {
        val tag: String
        when(eventtype) {
            XmlPullParser.START_DOCUMENT -> stops = ArrayList()
            XmlPullParser.START_TAG -> {
                tag = parser.name
                if(tag == "NextBus") {
                    nextBus = NextBus()
                } else if(nextBus != null) {
                    if(tag == "RouteNo") {
                        nextBus.routeNo = parser.nextText()
                    } else if(tag == "RouteName") {
                        nextBus.routeName = parser.nextText()
                    } else if(tag == "Direction") {
                        nextBus.direction = parser.nextText()
                    }
                }
                if(tag == "Schedule") {
                    stopEstimate = StopEstimate()
                } else if(stopEstimate != null) {
                    if(tag == "Destination") {
                        stopEstimate.destination = parser.nextText()
                    } else if(tag == "ExpectedLeaveTime") {
                        stopEstimate.expectedLeaveTime = parser.nextText()
                    } else if(tag == "ExpectedCountdown") {
                        stopEstimate.expectedCountdown = parser.nextText()
                    } else if(tag == "LastUpdate") {
                        stopEstimate.lastUpdate = parser.nextText()
                    }
                }
            }
            XmlPullParser.END_TAG -> {
                tag = parser.name
                if(tag.equals("Schedule", ignoreCase = true) && stopEstimate != null) {
                    stops!!.add(stopEstimate)
                    stopEstimate = null
                } else if(tag.equals("NextBus", ignoreCase = true) && nextBus != null) {
                    hash.put(nextBus, stops!!)
                    nextBus = null
                    stops = ArrayList()
                }
            }
        }
        eventtype = parser.next()
    }
    return hash
}



