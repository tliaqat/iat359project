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