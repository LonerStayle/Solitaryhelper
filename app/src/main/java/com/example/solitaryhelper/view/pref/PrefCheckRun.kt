package com.example.solitaryhelper.view.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PrefCheckRun private constructor (val context: Context){

    enum class Key {
        APP_GUIDE_FIRST_RUN,
        ID_EMPTY_CHECK,
        CALL_DELAY_COTROL,
        KAKAO_TALK_FIRST_RUN_CHECK,
        KAKAO_CHAT_NAV_DEEP_LINK_USE_CHECK,
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

    var idEmptyCheck:Boolean
    get() = pref.getBoolean(Key.ID_EMPTY_CHECK.name,false)
    set(value) {
        pref.edit().putBoolean(Key.ID_EMPTY_CHECK.name,
            value).apply()
    }

    var callDelayCotrol:Int
    get() = pref.getInt(Key.CALL_DELAY_COTROL.name,0)
    set(value) {
        pref.edit().putInt(Key.CALL_DELAY_COTROL.name,value).apply()
    }

    var kaKaoTalkFirstRunCheck:Boolean
        get() = pref.getBoolean(Key.KAKAO_TALK_FIRST_RUN_CHECK.name,false)
        set(value) {
            pref.edit().putBoolean(Key.KAKAO_TALK_FIRST_RUN_CHECK.name,
                value).apply()
        }

    var kaKaoChatNavDeepLinkUseCheck:Boolean
        get() = pref.getBoolean(Key.KAKAO_CHAT_NAV_DEEP_LINK_USE_CHECK.name,false)
        set(value) {
            pref.edit().putBoolean(Key.KAKAO_CHAT_NAV_DEEP_LINK_USE_CHECK.name,
                value).apply()
        }


}


