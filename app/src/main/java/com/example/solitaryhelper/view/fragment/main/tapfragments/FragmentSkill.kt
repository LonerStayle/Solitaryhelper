package com.example.solitaryhelper.view.fragment.main.tapfragments

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerSkill
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentSkill : BaseFragment<FragmentSkillBinding>(R.layout.fragment_skill) {

    private val viewModel_main by lazy{
        ViewModelProvider(this).get(MainViewModel::class.java) }
    //    data class ViewPagerItem(
//        var image: String,
//        var text: String
//    )
    private val textList by lazy {
        resources.getStringArray(R.array.skillToItem_list)
    }
//    private var imageList: Array<String>? = null


    override fun FragmentSkillBinding.setEventListener() {
        viewPagerListener()
    }

    override fun FragmentSkillBinding.setCreateView() {
        /**
         * fixme: 뷰페이저 오류 해결후 구문 삭제 대기.
         */

        setAdapter()
    }

    private fun FragmentSkillBinding.setAdapter() {
//      val itemList = mutableListOf<ViewPagerItem>()
//        imageList = Array(5) { "" }
//        for (i in imageList!!.indices) {
//            imageList!![i] += (resources.getIdentifier(
//                "sample$i",
//                "drawable",
//                requireActivity().packageName
//            ).toString())
//            itemList.add(ViewPagerItem(imageList!![i], textList[i]))

        viewPagerFakekakaoTalkInfo.adapter = AdapterViewPagerSkill(textList.toList())

    }

    private fun FragmentSkillBinding.viewPagerListener() {

        viewPagerFakekakaoTalkInfo.getChildAt(0).setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                when (p1?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        viewModel_main.viewPagerControl(false)
                        context?.toastDebugTest("실행 확인 1 ")

                    }
                    MotionEvent.ACTION_UP -> {
                        viewModel_main.viewPagerControl(true)
                        context?.toastDebugTest("실행 확인 2 ")
                        p0?.performClick()
                    }
                }
                return true
            }
        })
    }


}


