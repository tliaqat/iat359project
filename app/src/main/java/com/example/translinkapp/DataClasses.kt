package com.example.translinkapp

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

class Stop(
    @JvmField var name: String? = null,
    @JvmField var routes: String? = null,
    @JvmField var stopNo: String? = null,
    @JvmField var city: String? = null
)

data class StopEstimate(
    @JvmField val time: String,
    @JvmField var direction: Direction,
    @JvmField val route: String
)