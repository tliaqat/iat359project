package com.example.translinkapp

class Stop(
    @JvmField var name: String? = null,
    @JvmField var routes: String? = null,
    @JvmField var stopNo: String? = null,
    @JvmField var city: String? = null,
    @JvmField var isFavourite: Boolean = false
)

data class StopEstimate(
    @JvmField var destination: String? = null,
    @JvmField var expectedLeaveTime: String? = null,
    @JvmField var expectedCountdown: String? = null,
    @JvmField var lastUpdate: String? = null
)

data class NextBus(
    @JvmField var routeNo: String? = null,
    @JvmField var routeName: String? = null,
    @JvmField var direction: String? = null
)