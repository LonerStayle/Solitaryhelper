package com.example.solitaryhelper.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.solitaryhelper.R

import com.example.solitaryhelper.view.pref.PrefCheckRun

var autoChatRun:Boolean?=null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PrefCheckRun.getInstance(this).kaKaoChatNavDeepLinkUseCheck)
            PrefCheckRun.getInstance(this).kaKaoTalkFirstRunCheck = false

        //        getAppKeyHash()
    }

//    fun getAppKeyHash() {
//        try {
//            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//            for (i in info.signatures) {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(i.toByteArray())
//
//                val something = String(Base64.encode(md.digest(), 0)!!)
//                Log.e("Debug key", something)
//            }
//        } catch (e: Exception) {
//            Log.e("Not found", e.toString())
//        }
//    }
//    ST0FQ6GHZF3HQCfm93DR1ZJFlv4=


}

