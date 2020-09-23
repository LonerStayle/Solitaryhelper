package com.example.solitaryhelper.view.dest.fake_call

import android.text.TextUtils
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallSettingBinding

import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.keyBoardShowHiding
import com.example.solitaryhelper.view.utill.toastDebugTest

class FragmentFakeCallSetting :
    BaseFragment<FragmentFakeCallSettingBinding>(R.layout.fragment_fake_call_setting) {

//    private var spinnerAdapterArray: Array<TimeSpinnerModel>? = null


    override fun FragmentFakeCallSettingBinding.setEventListener() {
//        setSpinnerItemSelectListener()
        setButtonCallStartListener()

    }


    override fun FragmentFakeCallSettingBinding.setCreateView() {
        keyBoardShowHiding(requireContext(), editTextNameWrite)
        numberPickerSetting()
//        setSpinnerAdapter()

    }

    private fun FragmentFakeCallSettingBinding.setNumberPickerDelayNoticationEnabledSelectListener() {
        when (numberPickerDelayNoticationEnabled.value) {
            1 -> PrefCheckRun.getInstance(requireContext()).callDelayCotrol = Contents.CALL_DELAY_NOTICATION_ENABLED_OFF
            2 -> PrefCheckRun.getInstance(requireContext()).callDelayCotrol = Contents.CALL_DELAY_NOTICATION_ENABLED_ON
        }
    }

    private fun FragmentFakeCallSettingBinding.setButtonCallStartListener() {
        buttonCallStart.setOnClickListener {
//            Log.d("opop4","넘버피커 값:${numberPickerDelayNoticationEnabled.value}\n" +
//                    "딜레이 값 ${PrefCheckRun.getInstance(requireContext()).callDelayCotrol}")
            if (TextUtils.isEmpty(editTextNameWrite.text.toString()))
                context?.toastDebugTest("수신 받을 가짜 이름을 적어주세요")

            else {
                findNavController().navigate(
                    FragmentFakeCallSettingDirections.actionFragmentFakeCallGuideToFragmentFakeCall(
                        callPartyName = editTextNameWrite.text.toString(),
                        callMode = numberPickerNoticeSetting.value,
                        callNotication = numberPickerDelayNoticationEnabled.value

                    )
                )
                setNumberPickerDelayNoticationEnabledSelectListener()
            }
        }
    }

    private fun FragmentFakeCallSettingBinding.numberPickerSetting() {
        numberPickerNoticeSetting.displayedValues = arrayOf("무음", "진동", "벨소리","")
        numberPickerDelayNoticationEnabled.displayedValues = arrayOf("OFF", "ON")

    }

}
//    private fun FragmentFakeCallSettingBinding.setSpinnerAdapter() {
//        val spinnerTimeTableDisplay = arrayOf("30초 후", "1분 후", "1분 30초 후,2분 후,3분 후,5분 후", "선택 안함")
//        val timeSetArray = arrayOf(30000L, 60000L, 90000L, 120000L, 180000L, 300000L, 0L)
//
//
//        spinnerAdapterArray = Array(timeSetArray.size) { TimeSpinnerModel(1L, "") }
//
//        for (i in spinnerTimeTableDisplay.indices) {
//            spinnerAdapterArray!![i].timeSetArray = timeSetArray[i]
//            spinnerAdapterArray!![i].spinnerTimeTableDisplay = spinnerTimeTableDisplay[i]
//        }
//
//        val spinnerAdapter = ArrayAdapter(
//            requireContext(),
//            R.layout.support_simple_spinner_dropdown_item,
//            spinnerAdapterArray!!
//        )
//        spinnerTimeSetting.adapter = spinnerAdapter
//    }

//private fun FragmentFakeCallSettingBinding.setSpinnerItemSelectListener() {
//    spinnerTimeSetting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//        override fun onNothingSelected(p0: AdapterView<*>?) {
//            sendToTimer = null
//        }
//
//        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//            when (p2) {
//
//                0 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![0].timeSetArray
//                }
//                1 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![1].timeSetArray
//                }
//                2 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![2].timeSetArray
//                }
//                3 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![3].timeSetArray
//                }
//                4 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![4].timeSetArray
//                }
//                5 -> {
//                    sendToTimer = null; sendToTimer = spinnerAdapterArray!![5].timeSetArray
//                }
//                6 -> {
//                    sendToTimer = null; numberPickerTimeEnabled.value = 0
//                }
//
//            }
//        }
//
//    }
//}


