package com.example.translinkapp


import android.text.InputType
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

class stop(
    @JvmField var stop: Array<Stoppe>? = null
)

//@JsonRootName("Stop")
//@Root(name = "STOP", strict = false)
class Stoppe(
//    @set:JsonProperty("Name")
//    @field:ElementList(name = "Name", required = false)
    @JvmField var Name: String? = null,

//    @set:JsonProperty("Routes")
//    @field:ElementList(name = "Routes", required = false)
    @JvmField var Routes: List<String>? = null,

//    @set:JsonProperty("StopNo")
//    @field:ElementList(name = "StopNo", required = false)
    @JvmField var StopNo: String? = null,

//    @set:JsonProperty("City")
//    @field:ElementList(name = "City", required = false)
    @JvmField var City: String? = null

)

data class StopEstimate(val time: String, var direction: Direction, val route: String)