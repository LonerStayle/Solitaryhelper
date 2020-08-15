package com.example.solitaryhelper.view.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.json.JSONArray
import org.json.JSONException
import java.lang.reflect.Array.set

class PrefCheckPosition private constructor(val context: Context) {

    enum class Key {
        POSITION_VALUE
    }

    companion object {
        private var INSTANCE: PrefCheckPosition? = null

        fun getInstance(context: Context): PrefCheckPosition {
            return INSTANCE ?: PrefCheckPosition(context = context).also {
                INSTANCE = it
            }
        }
    }

    val pref = context.getSharedPreferences(context.packageName, MODE_PRIVATE)

    var positionCheck: MutableList<Int>?
        get() {
            val jsonString = pref.getString(Key.POSITION_VALUE.name, null)
            val urls = mutableListOf<Int>()

            if (jsonString != null) {
                try {
                    val position = JSONArray(jsonString)

                    for (i in 0 until position.length()) {
                        val url = position.optString(i)
                        urls.add(
                            i,
                            url.toInt()
                        )
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return urls
        }
        set(value) {
            val position = JSONArray()
            position.put(value)

            pref.edit().putString(Key.POSITION_VALUE.name, position.toString()).apply()
        }
}




