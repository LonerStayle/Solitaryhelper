package com.example.solitaryhelper.view.dest.fake_sms.settingfragments

import android.opengl.Visibility
import android.view.View
import android.view.animation.AnimationUtils
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsSettingPamphletBinding
import com.example.solitaryhelper.view.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentFakeSmsSettingPamphlet :
    BaseFragment<FragmentFakeSmsSettingPamphletBinding>(R.layout.fragment_fake_sms_setting_pamphlet) {

//    private val imageAnim by lazy{
//        AnimationUtils.loadAnimation(requireContext(),R.anim.sms_pamphletanim)
//    }
    override fun FragmentFakeSmsSettingPamphletBinding.setEventListener() {

    }

    override fun FragmentFakeSmsSettingPamphletBinding.setCreateView() {


//            imageViewFirst.startAnimation(imageAnim)
            imageViewTwo.visibility = View.VISIBLE
            textViewTwo.visibility = View.VISIBLE
//            imageViewTwo.startAnimation(imageAnim)

//            imageViewThiree.startAnimation(imageAnim)
            imageViewThiree.visibility = View.VISIBLE
            textViewThree.visibility = View.VISIBLE

        }


    }
