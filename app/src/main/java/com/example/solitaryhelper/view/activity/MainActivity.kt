package com.example.solitaryhelper.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.pref.PrefCheckRun

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        fun RecyclerView.disableItemAnimator() {
//            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
//        }
//
//        // sample of using in Activity:
//        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                                  savedInstanceState: Bundle?): View? {
//            myRecyclerView.disableItemAnimator()
//            // ...
//        }

    }

    override fun onStop() {

        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun2 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun3 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun4 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun5 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun6 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun7 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun8 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun9 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun10 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun11 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun12 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun13 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun14 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun15 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun16 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun17 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun18 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun19 = false
        PrefCheckRun.getInstance(this).kakaoChatObserverControlFirstRun20 = false

        PrefCheckRun.getInstance(this).kaKaoTalkFirstRunCheck = false
        super.onStop()
    }

}