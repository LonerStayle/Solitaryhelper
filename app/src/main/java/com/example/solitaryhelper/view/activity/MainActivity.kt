package com.example.solitaryhelper.view.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.pref.PrefCheckRun
import java.security.MessageDigest


var autoChatRun = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoChatRun = true

        PrefCheckRun.getInstance(this@MainActivity).kaKaoTalkFirstRunCheck = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun2 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun3 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun4 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun5 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun6 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun7 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun8 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun9 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun10 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun11 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun12 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun13 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun14 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun15 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun16 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun17 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun18 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun19 = false
        PrefCheckRun.getInstance(this@MainActivity).kakaoChatObserverControlFirstRun20 = false
       
//        getAppKeyHash()

    }

    fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (i in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(i.toByteArray())

                val something = String(Base64.encode(md.digest(), 0)!!)
                Log.e("Debug key", something)
            }
        } catch (e: Exception) {
            Log.e("Not found", e.toString())
        }
    }
//    ST0FQ6GHZF3HQCfm93DR1ZJFlv4=

}

