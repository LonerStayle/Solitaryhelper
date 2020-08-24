package com.example.solitaryhelper.view.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PrefCheckRun private constructor (val context: Context){

    enum class Key {
        APP_GUIDE_FIRST_RUN,
        ID_EMPTY_CHECK,
        CALL_DELAY_COTROL,
        KAKAO_TALK_FIRST_RUN_CHECK,

        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN2,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN3,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN4,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN5,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN6,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN7,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN8,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN9,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN10,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN11,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN12,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN13,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN14,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN15,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN16,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN17,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN18,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN19,
        KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN20


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

    var kakaoChatObserverControlFirstRun:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun2:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN2.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN2.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun3:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN3.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN3.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun4:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN4.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN4.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun5:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN5.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN5.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun6:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN6.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN6.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun7:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN7.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN7.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun8:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN8.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN8.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun9:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN9.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN9.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun10:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN10.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN10.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun11:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN11.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN11.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun12:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN12.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN12.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun13:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN13.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN13.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun14:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN14.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN14.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun15:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN15.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN15.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun16:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN16.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN16.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun17:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN17.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN17.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun18:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN18.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN18.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun19:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN19.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN19.name,value).apply()
    }

    var kakaoChatObserverControlFirstRun20:Boolean
    get() = pref.getBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN20.name,false)
    set(value) {
        pref.edit().putBoolean(Key.KAKAO_CHAT_OBSERVER_CONTROL_FIRST_RUN20.name,value).apply()
    }


}