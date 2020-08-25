package com.example.solitaryhelper.view.dest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentAutochatrunBinding
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.activity.autoChatRun
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dest.fake_kakao.FragmentFakeKakaoChat.Companion.autoChatDoubleCheckRun
import kotlinx.android.synthetic.main.fragment_autochatrun.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentAutoChatRunCheck :BaseFragment<FragmentAutochatrunBinding>(R.layout.fragment_autochatrun) {




    override fun FragmentAutochatrunBinding.setEventListener() {

    }

    override fun FragmentAutochatrunBinding.setCreateView() {
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0..19)
            autoChatDoubleCheckRun[i] = false

            delay(1500L)
            requireActivity().finish()
        }

    }


}
