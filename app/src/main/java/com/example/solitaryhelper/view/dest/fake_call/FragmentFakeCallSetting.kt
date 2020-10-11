package com.example.solitaryhelper.view.dest.fake_call

import android.app.TimePickerDialog
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TimePicker
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallSettingBinding

import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.dialog.DialogCustom
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.keyBoardShowHiding
import com.example.solitaryhelper.view.utill.toastDebugTest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class FragmentFakeCallSetting :
    BaseFragment<FragmentFakeCallSettingBinding>(R.layout.fragment_fake_call_setting) {

    private var setAlarmTimeHour: Int? = null
    private var setAlarmTimeMinute: Int? = null
//    private var spinnerAdapterArray: Array<TimeSpinnerModel>? = null


    override fun FragmentFakeCallSettingBinding.setEventListener() {
//        setSpinnerItemSelectListener()

    }


    override fun FragmentFakeCallSettingBinding.setCreateView() {
        thisFragment = this@FragmentFakeCallSetting
        keyBoardShowHiding(requireContext(), editTextNameWrite)
        numberPickerSetting()
//        setSpinnerAdapter()

    }

    private fun FragmentFakeCallSettingBinding.setNumberPickerDelayNoticationEnabledSelectListener() {
        when (numberPickerDelayNoticationEnabled.value) {
            1 -> PrefCheckRun.getInstance(requireContext()).callDelayCotrol =
                Contents.CALL_DELAY_NOTICATION_ENABLED_OFF
            2 -> PrefCheckRun.getInstance(requireContext()).callDelayCotrol =
                Contents.CALL_DELAY_NOTICATION_ENABLED_ON
        }
    }

    fun setButtonCallStartListener(v: View) {

//            Log.d("opop4","넘버피커 값:${numberPickerDelayNoticationEnabled.value}\n" +
//                    "딜레이 값 ${PrefCheckRun.getInstance(requireContext()).callDelayCotrol}")

        when {
            TextUtils.isEmpty(binding.editTextNameWrite.text.toString()) -> {
                context?.toastDebugTest("수신 받을 가짜 이름을 적어주세요")
                return
            }

            PrefCheckRun.getInstance(requireContext()).callDelayCotrol ==
                    Contents.CALL_DELAY_NOTICATION_ENABLED_ON &&
                    setAlarmTimeHour == null || setAlarmTimeMinute == null -> {
                context?.toastDebugTest("알람 받을 시간을 설정하거나\n혹은 알람 기능을 OFF 해주세요")
                return
            }

        }

        binding.apply {
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

    private fun FragmentFakeCallSettingBinding.numberPickerSetting() {
        numberPickerNoticeSetting.displayedValues = arrayOf("무음", "진동", "벨소리", "")
        numberPickerDelayNoticationEnabled.displayedValues = arrayOf("OFF", "ON")

    }

    fun setButtonTimePickerClickListener(v: View) {
        val currentHour = SimpleDateFormat("hh", Locale.KOREA).format(Date()).toInt()
        val currentMinute = SimpleDateFormat("mm", Locale.KOREA).format(Date()).toInt()
        val callback = TimePickerDialog.OnTimeSetListener { TimePicker, hour, minute ->
            setAlarmTimeHour = hour
            setAlarmTimeMinute = minute
            Log.d("test", "$setAlarmTimeHour,$setAlarmTimeMinute")
        }
        TimePickerDialog(
            requireContext(),
            R.style.Theme_MaterialComponents_DayNight_Dialog_Alert,
            callback,
            currentHour,
            currentMinute,
            false
        ).show()
    }

}



