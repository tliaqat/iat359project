package com.example.translinkapp

import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var myStops = ArrayList<Stop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(stop in getFavourites(this)!!) {
            myStops.add(callStop(stop, this))
        }

        val searchStopNo = findViewById<EditText>(R.id.myStopNo)
        searchStopNo.setOnKeyListener { v, keyCode, event ->
            onKeyListener(keyCode, event, searchStopNo)
        }

        val favRv = findViewById<RecyclerView>(R.id.favRecyclerView)
        favRv.layoutManager = LinearLayoutManager(this)
        favRv.adapter = FavouriteAdapter(myStops, this)

    }

    private fun onKeyListener(keyCode: Int, event: KeyEvent, search: EditText) : Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
            val s = callStop(search.text.toString(), this)
            var addToList = true
            if(s.name != null) {
                for(x in myStops) {
                    if(s.stopNo == x.stopNo) {
                        toastMessage("Already in your list", this)
                        addToList = false
                        break
                    }
                }
                if(addToList) {
                    myStops.add(s)
                    toastMessage("ADDED TO YOUR LIST", this)
                }
            } else {
                toastMessage("INVALID STOP #", this)
            }
            return true
        }
        return false
    }
}

