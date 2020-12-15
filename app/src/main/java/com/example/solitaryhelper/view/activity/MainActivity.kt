package com.example.solitaryhelper.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityMainBinding
import com.example.solitaryhelper.view.base.BaseActivity
import com.example.solitaryhelper.view.pref.PrefCheckRun

var autoChatRun:Boolean?=null

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun ActivityMainBinding.setData() {
        if (!PrefCheckRun.getInstance(this@MainActivity).kaKaoChatNavDeepLinkUseCheck)
            PrefCheckRun.getInstance(this@MainActivity).kaKaoTalkFirstRunCheck = false
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

