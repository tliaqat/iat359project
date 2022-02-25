package com.example.translinkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callStopEstimates("52851")
        callStop("59316")

    }


}

//<Stop xmlns:i="http://www.w3.org/2001/XMLSchema-instance"><StopNo>59316</StopNo><Name>PRODUCTION STATION BAY 3</Name><BayNo>3</BayNo><City>BURNABY</City><OnStreet>PRODUCTION STATION</OnStreet><AtStreet>BAY 3</AtStreet><Latitude>49.254032</Latitude><Longitude>-122.918229</Longitude><WheelchairAccess>1</WheelchairAccess><Distance>-1</Distance><Routes>110, 136</Routes></Stop>