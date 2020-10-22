package com.example.solitaryhelper.view.dest.main.tapfragments

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSkill
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.viewmodel.SkillViewModel

class FragmentSkill : BaseFragment<FragmentSkillBinding>(R.layout.fragment_skill) {

    private val viewModelSkill by viewModels<SkillViewModel>()

    data class ViewPagerItem(
        var image: String,
        var text: String
    )

    private val kakaoTextList by lazy {
        resources.getStringArray(R.array.kaKaoExampleList)
    }
    private val callTextList by lazy {
        resources.getStringArray(R.array.callExampleList)
    }
    private val smsTextList by lazy {
        resources.getStringArray(R.array.smsExampleList)
    }


    private var kakaoImageList: Array<String>? = null
    private var callImageList: Array<String>? = null
    private var smsImageList: Array<String>? = null

    override fun FragmentSkillBinding.setCreateView() {
        thisFragment = this@FragmentSkill
        setAdapter()
        setViewPagerAnim()
    }

    override fun FragmentSkillBinding.setEventListener() {
        setTouchEvent()
    }



    private fun FragmentSkillBinding.setViewPagerAnim() {

        viewModelSkill.setViewPagerSetting(viewPagerFakekakaoTalkInfo, requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeSmsInfo, requireContext())
        viewModelSkill.setViewPagerSetting(viewPagerFakeCallInfo, requireContext())

    }

    private fun FragmentSkillBinding.setAdapter() {
        val kakaoitemList = mutableListOf<ViewPagerItem>()
        val callitemList = mutableListOf<ViewPagerItem>()
        val smsitemList = mutableListOf<ViewPagerItem>()

        kakaoImageList = Array(6) { "" }
        for (i in 5 downTo 0) {
            kakaoImageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "guide_kakao${i}",
                "drawable",
                requireActivity().packageName
            ).toString())
            kakaoitemList.add(ViewPagerItem(kakaoImageList!![i], kakaoTextList[i]))
        }
        callImageList = Array(4) { "" }
        for (i in 0..3) {
            callImageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "guide_call$i",
                "drawable",
                requireActivity().packageName
            ).toString())
            callitemList.add(ViewPagerItem(callImageList!![i], callTextList[i]))
        }

        smsImageList = Array(1) {
            (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "newsample0",
                "drawable",
                requireActivity().packageName
            ).toString())
        }
        smsitemList.add(ViewPagerItem(smsImageList!![0], smsTextList[0]))

        viewPagerFakekakaoTalkInfo.adapter = AdapterViewPagerSkill(kakaoitemList){setGoToTheKaKaoTalk(null)}
        viewPagerFakeCallInfo.adapter = AdapterViewPagerSkill(callitemList){setGoToTheCallSetting(null)}
        viewPagerFakeSmsInfo.adapter = AdapterViewPagerSkill(smsitemList){setGoToTheSmsSetting(null)}

    }

    @SuppressLint("ClickableViewAccessibility")
    fun FragmentSkillBinding.setTouchEvent() {

        fun touchEvent(afterLogic: () -> Unit): View.OnTouchListener {
            return View.OnTouchListener { view, motionEvent ->
                when (motionEvent.action) {

                    MotionEvent.ACTION_DOWN -> {
                        view.alpha = 0.5f
                        view.scaleY = 2.0f
                        view.scaleX = 2.0f
                    }

                    MotionEvent.ACTION_UP -> {
                        view.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f)
                            .setDuration(300L).withEndAction { afterLogic() }.start()
                    }

                    else -> {
                        view.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f)
                            .setDuration(200L).start()
                    }
                }
                true
            }
        }

        imageViewQuickKaKaoButton.setOnTouchListener(touchEvent
        { setGoToTheKaKaoTalk(null) })

        imageViewQuickCallButton.setOnTouchListener(touchEvent
        { setGoToTheCallSetting(null) })

        imageViewQuickSmsButton.setOnTouchListener(touchEvent
        { setGoToTheSmsSetting(null) })

    }

     fun setGoToTheKaKaoTalk(v:View?) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
    }

     fun setGoToTheCallSetting(v:View?) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCallSetting)
    }

     fun setGoToTheSmsSetting(v:View?) {
        findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSmsSetting)
    }

}




