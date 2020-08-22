package com.example.solitaryhelper.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.pref.PrefCheckRun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var firstRun = true
var autoChatRun = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoChatRun = true

        if (firstRun) {
            firstRun = false

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
        }
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

}