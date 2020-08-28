package com.example.solitaryhelper.view.dest.fake_sms.settingfragments

import android.graphics.Color
import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeSmsSettingCustomBinding
import com.example.solitaryhelper.localdb.entitiy.Sms
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewSmsCustom
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.toastDebugTest
import java.util.*

class FragmentFakeSmsSettingCustom :
    BaseFragment<FragmentFakeSmsSettingCustomBinding>(R.layout.fragment_fake_sms_setting_custom) {

    private var myTextMode = false
    private var setRecyclerViewAdapter = false

    override fun FragmentFakeSmsSettingCustomBinding.setEventListener() {
        setYourButtonClickListener()
        setButtonSendListener()
        setFABclickListener()
        buttonDelete.setOnClickListener {

            if((recyclerViewSmsSettingCustom.adapter as AdapterRecyclerViewSmsCustom).smsList.isNullOrEmpty()) {
                context?.toastDebugTest("리스트가 비어 있습니다.")
            }else
            viewModelSms.smsAllDelete((recyclerViewSmsSettingCustom.adapter as AdapterRecyclerViewSmsCustom).smsList)
        }
    }

    override fun FragmentFakeSmsSettingCustomBinding.setCreateView() {}

    override fun FragmentFakeSmsSettingCustomBinding.setLiveDataInObserver() {
        setObserver()
    }

    private fun FragmentFakeSmsSettingCustomBinding.setObserver() {
        viewModelSms.SmsList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.isNullOrEmpty())
                return@Observer

                recyclerViewSmsSettingCustom.adapter =
                    AdapterRecyclerViewSmsCustom(it as MutableList<Sms>)

        })
    }

    private fun FragmentFakeSmsSettingCustomBinding.setFABclickListener() {
        floatingActionButtonInfo.setOnClickListener {

            if (recyclerViewSmsSettingCustom.adapter == null) {
                context?.toastDebugTest("문자 창을 만들어주세요")
                return@setOnClickListener
            }

            findNavController().navigate(R.id.action_fragmentFakeSmsSetting_to_fragmentFakeSms)
        }
    }

    private fun FragmentFakeSmsSettingCustomBinding.setYourButtonClickListener() {
        var buttonClickMode = false
        buttonYourText.setOnClickListener {
            buttonClickMode = !buttonClickMode

            myTextMode = if (buttonClickMode) {
                it.setBackgroundColor(Color.parseColor("#FFFFFF"))
                true
            }else {
                it.setBackgroundColor(Color.parseColor("#000000"))
                false
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
                    false, Contents.timePattern.format(
                        Date()
                    )
                )
            }

        }
    }
}

