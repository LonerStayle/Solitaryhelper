package com.example.solitaryhelper.view.dest.fake_call

import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallAgreeScreenBinding
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class FragmentFakeCallAgreeScreen:BaseFragment<FragmentFakeCallAgreeScreenBinding>(R.layout.fragment_fake_call_agree_screen)
{
    private val args by lazy{ FragmentFakeCallAgreeScreenArgs.fromBundle(
        requireArguments()
    )}
    companion object{
        var time = 0
        val timer = Timer()
    }

    override fun FragmentFakeCallAgreeScreenBinding.setEventListener() {
        setButtonClearClickListener()
    }

    override fun FragmentFakeCallAgreeScreenBinding.setCreateView() {

        setData()
        val timeTask = object :TimerTask(){
            override fun run() {
                time ++

                requireActivity().runOnUiThread(){
                    miunet =String.format("%02d",time/ 60 % 60)
                   second = String.format("%02d", time % 60)
                }
            }
        }
        timer.schedule(timeTask,0,1000)
    }

    private fun FragmentFakeCallAgreeScreenBinding.setData() {
        name = args.name
        image = Contents.IMAGE_URL_DEFAULT_FILE_PATH+args.image
    }

    private fun FragmentFakeCallAgreeScreenBinding.setButtonClearClickListener() {
        buttonAllClaer.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                timer.cancel()
                delay(3000)
                requireActivity().finish()
            }

        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}