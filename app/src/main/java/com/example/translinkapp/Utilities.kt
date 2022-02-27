package com.example.translinkapp

import android.content.Context
import android.widget.Toast


fun setfavourites(context: Context, arr: MutableSet<String>) {
    val preferences = context.getSharedPreferences("PREFERENCE_NAME", 0)
    val editor = preferences.edit()
    editor.clear()
    editor.putStringSet("favs", arr)
    editor.apply()

}

fun getFavourites(context: Context): MutableSet<String>? {
    val x = context.getSharedPreferences("PREFERENCE_NAME", 0).getStringSet("favs", mutableSetOf())
    return x
}

fun toastMessage(str: String, context: Context) {
    Toast.makeText(context, str, Toast.LENGTH_LONG).show()
}

fun addToSharedPref(stop: Stop, context: Context) {
    val x = getFavourites(context)
    x!!.add(stop.stopNo!!)
    setfavourites(context, x)
}

fun removeFromSharedPref(stop: Stop, context: Context) {
    val x = getFavourites(context)
    if(x!!.contains(stop.stopNo!!)) {
        x.remove(stop.stopNo)
    }
    setfavourites(context, x)
}