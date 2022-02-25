package com.example.translinkapp


//import org.xmlpull.v1.XmlPullParser
//import org.xmlpull.v1.XmlPullParserFactory
//import java.io.InputStream


import android.R.attr.host
import android.R.attr.port
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister


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
    println("WE HERE " + xmlData)
    var stop = XMLParser(stop::class.java).fromXML(xmlData)
    println("WE AIN HERE " + stop.stop)

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

