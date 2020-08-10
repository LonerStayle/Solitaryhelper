package com.example.solitaryhelper.view.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PrefCheckRun private constructor (val context: Context){

    enum class Key {
        APP_GUIDE_FIRST_RUN,
        MAIN_CREATE_ID_CHECK
    }
    companion object{
        private var instance:PrefCheckRun? = null

        fun getInstance(context: Context):PrefCheckRun = instance?:PrefCheckRun(context).also {
            instance = it
        }
    }

    private val pref = context.getSharedPreferences(context.packageName,MODE_PRIVATE)

    var appGuideFirstRunUserMark:Boolean
    get() = pref.getBoolean(Key.APP_GUIDE_FIRST_RUN.name,false)
    set(value) {
        pref.edit().putBoolean(Key.APP_GUIDE_FIRST_RUN.name,
            value).apply()
    }

    var mainCreateId:Boolean
    get() = pref.getBoolean(Key.MAIN_CREATE_ID_CHECK.name,false)
    set(value) {
        pref.edit().putBoolean(Key.MAIN_CREATE_ID_CHECK.name,
            value).apply()
    }

}