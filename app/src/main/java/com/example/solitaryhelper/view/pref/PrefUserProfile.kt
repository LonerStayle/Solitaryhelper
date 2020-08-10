package com.example.solitaryhelper.view.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PrefUserProfile private constructor(var context: Context){

    enum class Key{
        USER_ID
    }

    companion object{
        private var INSTANCES:PrefUserProfile? = null

        fun getInstance(context: Context):PrefUserProfile = INSTANCES?: PrefUserProfile(context).also {
            INSTANCES = it
        }
    }

    private val pref = context.getSharedPreferences(context.packageName,MODE_PRIVATE)

    var userId:String
    get() = pref.getString(Key.USER_ID.name,"")!!
    set(value) {
        pref.edit().putString(Key.USER_ID.name,value).apply()
    }
}