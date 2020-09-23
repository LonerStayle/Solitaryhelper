package com.example.solitaryhelper.view.dest.fake_call

import android.content.Intent
import android.net.Uri
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallAgreeScreenBinding
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.utill.toastDebugTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class FragmentFakeCallAgreeScreen :
    BaseFragment<FragmentFakeCallAgreeScreenBinding>(R.layout.fragment_fake_call_agree_screen) {
    private val args by lazy {
        FragmentFakeCallAgreeScreenArgs.fromBundle(
            requireArguments()
        )
    }

       private var time = 0
        private val timer: Timer? = Timer()


    override fun FragmentFakeCallAgreeScreenBinding.setEventListener() {
        setButtonClearClickListener()
    }

    override fun FragmentFakeCallAgreeScreenBinding.setCreateView() {

        setData()
        setTime()


    }

    private fun FragmentFakeCallAgreeScreenBinding.setTime() {
        val timeTask = object : TimerTask() {
            override fun run() {
                time++

                requireActivity().runOnUiThread() {
                    miunet = String.format("%02d", time / 60 % 60)
                    second = String.format("%02d", time % 60)
                }
            }
        }
        timer?.schedule(timeTask, 0, 1000)
    }

    private fun FragmentFakeCallAgreeScreenBinding.setData() {
        name = args.name
    }

    private fun FragmentFakeCallAgreeScreenBinding.setButtonClearClickListener() {
        buttonAllClaer.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                context?.toastDebugTest("통화가 종료됩니다.")
                textViewMiunet.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_call_end_24_mini,
                    0,
                    0,
                    0
                )
                timer?.cancel()
                delay(2000)
                val intent = Intent(requireActivity(),MainActivity::class.java)
                requireActivity().finish()
                startActivity(intent)
            }

        }
    }

    override fun onDestroy() {
        timer?.cancel()
        time = 0
        super.onDestroy()
    }
}