package com.example.solitaryhelper.view.dest.main.tapfragments

import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentTopicBinding
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dest.main.MainFragmentDirections
import com.example.solitaryhelper.view.utill.toastDebugTest

class FragmentTopic : BaseFragment<FragmentTopicBinding>(R.layout.fragment_topic) {
    private val genders = arrayOf("남성", "여성")
    private var selectGenders: String? = null

    override fun FragmentTopicBinding.setEventListener() {
        setSeekBarListener()
        setButtonSearch()
        setSpinnerListener()
    }

    override fun FragmentTopicBinding.setCreateView() {
        setSpinnerAdapter()
    }

    private fun FragmentTopicBinding.setButtonSearch() {
        buttonSearch.setOnClickListener {
            if (TextUtils.isEmpty(selectGenders))
                context?.toastDebugTest("대화할 성별을 선택해주세요")
            else
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToFragmentTopicResult(
                        ageText!!.toInt(), selectGenders!!
                    )
                )
        }
    }

    private fun FragmentTopicBinding.setSpinnerListener() {
        spinnerGenderSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {selectGenders = genders[0]}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> selectGenders = genders[0]
                    1 -> selectGenders = genders[1]
                }
            }

        }
    }

    private fun FragmentTopicBinding.setSpinnerAdapter() {
        spinnerGenderSelect.adapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, genders)
    }

    private fun FragmentTopicBinding.setSeekBarListener() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                when (p1) {
                    0 -> ageText = "중,고등학생"
                    1 -> ageText = "2030청년"
                    2 -> ageText = "4050중년"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }
}