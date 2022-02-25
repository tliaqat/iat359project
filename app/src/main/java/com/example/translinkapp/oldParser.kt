package com.example.translinkapp


//import org.xmlpull.v1.XmlPullParser
//import org.xmlpull.v1.XmlPullParserFactory
//import java.io.InputStream


import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


//internal val kotlinXmlMapper = XmlMapper(JacksonXmlModule().apply {
//    setDefaultUseWrapper(false)
//}).registerKotlinModule()
//    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
//    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


fun parseStop(xmlData: String) {

//    val test = kotlinXmlMapper.readValue<Stop>(xmlData)
//    println(test.city)

//    val serializer: Serializer = Persister()
//    val dataFetch = serializer.read(Stop::class.java, xmlData)
//    println(dataFetch.city)
//    println("WE HERE " + xmlData)
//    var stop = XMLParser(stop::class.java).fromXML(xmlData)
//    println("WE AIN HERE " + stop.stop)

    val pullParserFactory: XmlPullParserFactory
    try {
        pullParserFactory = XmlPullParserFactory.newInstance()
        val parser = pullParserFactory.newPullParser()
        val inputStream = xmlData.byteInputStream()

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)

        val stops = parseXml(parser)
        var text = ""

        for(stop in stops!!) {
            text += "name: " + stop.Routes[0]
        }
        println("THIS IS WHO WE ARE " + stops[0].City)
    } catch (e: XmlPullParserException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }

//    var factory = XmlPullParserFactory.newInstance()
//    var parser = factory.newPullParser()
//
//    parser.setInput(xmlData, null)
//
//    var event = parser.eventType
//    println("In Parse")
//    while (event != XmlPullParser.END_DOCUMENT) {
//        var tagName = parser.name
//        println(tagName)
//        event = parser.next()
//    }

}

@Throws(XmlPullParserException::class, IOException::class)
fun parseXml(parser: XmlPullParser) : ArrayList<Stop>? {
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
                        stop.Name = parser.nextText()
                    } else if(name =="StopNo") {
                        stop.StopNo = parser.nextText()
                    } else if(name == "City") {
                        stop.City = parser.nextText()
                    } else if(name == "Routes") {
                        stop.Routes.add(parser.nextText())
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



