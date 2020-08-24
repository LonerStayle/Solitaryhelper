package com.example.solitaryhelper.view.dest.fake_sms.settingfragments

import android.graphics.Color
import android.text.TextUtils
import android.widget.Toast
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsSettingCustomBinding
import com.example.solitaryhelper.localdb.entitiy.Sms
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewSmsCustom
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import java.util.*

class FragmentFakeSmsSettingCustom :
    BaseFragment<FragmentFakeSmsSettingCustomBinding>(R.layout.fragment_fake_sms_setting_custom) {

    private var myTextMode = false
    private var setRecyclerViewAdapter = false
    override fun FragmentFakeSmsSettingCustomBinding.setEventListener() {
        setYourButtonClickListener()
        setButtonSendListener()
    }


    override fun FragmentFakeSmsSettingCustomBinding.setCreateView() {

    }

    override fun FragmentFakeSmsSettingCustomBinding.setLiveDataInObserver() {
        viewModelSms.SmsList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it ?: return@Observer
            if (!setRecyclerViewAdapter) {
                recyclerViewSmsSettingCustom.adapter =
                    AdapterRecyclerViewSmsCustom(it.toMutableList())
                setRecyclerViewAdapter = true

            }
            if (setRecyclerViewAdapter ) {
                (recyclerViewSmsSettingCustom.adapter as? AdapterRecyclerViewSmsCustom)?.apply {
                    this.smsList.add(it.last())
                    notifyItemInserted(it.lastIndex)
                }
            }
        })
    }


    private fun FragmentFakeSmsSettingCustomBinding.setYourButtonClickListener() {
        var buttonClickMode = false
        buttonYourText.setOnClickListener {
            buttonClickMode = !buttonClickMode
            if (myTextMode)
                it.setBackgroundColor(Color.parseColor("#FFF"))
            else {
                it.setBackgroundColor(Color.parseColor("#000"))
            }
        }
    }

    private fun FragmentFakeSmsSettingCustomBinding.setButtonSendListener() {
        buttonSend.setOnClickListener {
            if (TextUtils.isEmpty(editTextMessage.text)) {
                Toast.makeText(requireContext(), "생성할 메세지를 적어주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (myTextMode) {
                viewModelSms.smsInsert(
                    editTextMessage.text.toString(),
                    true, Contents.timePattern.format(
                        Date()
                    )
                )
            } else {

                viewModelSms.smsInsert(
                    editTextMessage.text.toString(),
                    true, Contents.timePattern.format(
                        Date()
                    )
                )
            }

        }
    }
}

