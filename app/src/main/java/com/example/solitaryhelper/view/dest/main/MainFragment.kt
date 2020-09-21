package com.example.solitaryhelper.view.dest.main


import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.databinding.HeaderNavigationMainBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerMain
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dialog.DialogCustom
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_fake_call_setting.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val iconImage by lazy {
        mutableListOf(
            R.drawable.ic_baseline_skill_24,
            R.drawable.ic_baseline_topic_24,
            R.drawable.ic_outline_settings_eating_alone_24,
            R.drawable.ic_outline_wise_saying_24,
            R.drawable.ic_baseline_chatbot_24
        )
    }
    private val iconSelectImage by lazy {
        mutableListOf(
            R.drawable.ic_baseline_skill_select_24,
            R.drawable.ic_baseline_topic_select_24,
            R.drawable.ic_outline_settings_eating_alone_select_24,
            R.drawable.ic_outline_wise_saying_select_24,
            R.drawable.ic_baseline_chatbot_select_24
        )
    }

    private val toolbarTitleList =
        arrayOf("아싸를 위한 스킬", "블로그로 최신 트렌드 읽기", "집 주변 혼밥 찾기", "감성 변태", "고민 상담")
    private val iconText by lazy { resources.getStringArray(R.array.icon_text) }
    private var fabStartEventInClickCheck = false
    private val naviIconBackground by lazy { R.drawable.ic_baseline_menu_24 }
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val naviHeaderBinding by lazy {
        DataBindingUtil.inflate<HeaderNavigationMainBinding>(
            layoutInflater, R.layout.header_navigation_main,
            binding.navigationViewMain, false
        )
    }

    private val dialog by lazy { DialogCustom(requireContext()) }


    override fun FragmentMainBinding.setCreateView() {

        setWindowUI()
        setToolbarSetting()
        setNavigationViewAndHeaderViewSetting()
        setViewPagerAndTabLayoutSetting()
        setIdCreate()

    }


    override fun FragmentMainBinding.setEventListener() {
        setTabIconClickListener()
        setFabCallClickListener()
        setFabKakaoTalkClickListener()
        setFabSmsClickListener()
        setFabStartClickListener()
        setToolbarButtonClickListener()
        setNavigationViewClickListener()
        setbackButtonClickListener()


    }


    private fun setWindowUI() {
        requireActivity().window.navigationBarColor = Color.GRAY
    }

    override fun FragmentMainBinding.setLiveDataInObserver() {
        profileIdSetting()
    }

    private fun profileIdSetting() {
        viewModelMain.userProfile.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                PrefCheckRun.getInstance(requireContext()).idEmptyCheck = false
                return@Observer
            } else {
                naviHeaderBinding.textViewId.text = it.userId
                PrefCheckRun.getInstance(requireContext()).idEmptyCheck = true
            }
        })
    }

    private fun FragmentMainBinding.setNavigationViewAndHeaderViewSetting() {
        navigationViewMain.addHeaderView(naviHeaderBinding.root)
    }

    private fun FragmentMainBinding.setViewPagerAndTabLayoutSetting() {

        viewPagerMain.adapter = AdapterViewPagerMain(this@MainFragment)
        TabLayoutMediator(tabLayoutMain, viewPagerMain) { tab, position ->

            for (i in 0..4) {
                when (position) {
                    i -> {
                        tab.text = iconText[i]
                        //앱 첫 시작시 포지션이 1 일때 색감이 안바뀌던것을 조절함
                        if (i == 0) {
                            tab.setIcon(iconSelectImage[i])
                            fabStart.hide()
                        } else
                            tab.setIcon(iconImage[i])
                    }
                }
            }
        }.attach()
        viewPagerMain.isUserInputEnabled = false
    }

    private fun FragmentMainBinding.setTabIconClickListener() {

        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {

                        i -> {
                            if (i == 0 || i == 4)
                                fabStart.hide()

                            tab.setIcon(iconSelectImage[i])
                            titleText = toolbarTitleList[i]
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                for (i in 0..4) {
                    when (tab?.position) {

                        i -> {
                            if (i == 0 || i == 4)
                                fabStart.show()
                            tab.setIcon(iconImage[i])
                        }

                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun FragmentMainBinding.setFabStartClickListener() {

        fabStart.setOnClickListener {
            fabStartEventInClickCheck = !fabStartEventInClickCheck
            if (fabStartEventInClickCheck) {
                fabStart.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)

                uiScope.launch {
                    fabSms.show()
                    delay(100)
                    fabCall.show()
                    delay(200)
                    fabKakaoTalk.show()
                }

            } else {
                fabStart.setImageResource(R.drawable.ic_round_play_arrow_24)
                uiScope.launch {
                    fabKakaoTalk.hide()
                    delay(100)
                    fabCall.hide()
                    delay(200)
                    fabSms.hide()
                }
            }
        }
    }

    private fun FragmentMainBinding.setToolbarButtonClickListener() {
        toolBar.setNavigationOnClickListener {
            drawerLayoutMain.open()
        }
    }

    private fun FragmentMainBinding.setToolbarSetting() {
        with(toolBar) {
            setNavigationIcon(naviIconBackground)
            titleText = toolbarTitleList[0]
        }
    }


    private fun setNavigationViewClickListener() = with(binding.navigationViewMain) {
        setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_wiseSayingList -> {
                }
                R.id.item_ranking -> {
                }
                R.id.item_helperInWishList -> {
                }
                R.id.item_contactTheDeveloper -> {
                }
                R.id.item_setting -> {
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }

    private fun FragmentMainBinding.setFabSmsClickListener() {

        fabSms.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeSms)
        }
    }

    private fun FragmentMainBinding.setFabKakaoTalkClickListener() {
        fabKakaoTalk.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeKakaoTalk)
        }
    }

    private fun FragmentMainBinding.setFabCallClickListener() {

        fabCall.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentFakeCall)
        }
    }


    private fun setbackButtonClickListener() {
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(this@MainFragment) {
                if (drawerLayoutMain.isOpen)
                    drawerLayoutMain.closeDrawers()
                else {
                    requireActivity().finish()
                }

            }
        }
    }

    private fun setIdCreate() {
        val textId = dialog.dialogMainIdCreateBinding.editTextCreateId.text
        fun logicInMakeId() {
            if (TextUtils.isEmpty(textId))
                context?.toastDebugTest("닉네임을 설정해주세요.")
            else {
                viewModelMain.insertUserProfileId(textId.toString())
                dialog.dialogCreate.dismiss()
            }
        }

        setButtonIdChangeClickListener { logicInMakeId() }

        if (!PrefCheckRun.getInstance(requireContext()).idEmptyCheck) {

            dialog.dialogMainIdCreate()

            dialog.dialogMainIdCreateBinding.buttonCreateId.setOnClickListener {
                logicInMakeId()

            }
            dialog.dialogCreate.show()
        }
    }

    private fun setButtonIdChangeClickListener(logic: () -> Unit) {

        naviHeaderBinding.buttonIdChange.setOnClickListener {
            dialog.dialogMainIdCreate()
            dialog.dialogCreate.show()

            dialog.dialogMainIdCreateBinding.buttonCreateId.setOnClickListener {
                logic()
            }
        }
    }

    override fun onResume() {
        if(binding.navigationViewMain.getHeaderView(0) == null)
            binding.navigationViewMain.addHeaderView(naviHeaderBinding.root)
        super.onResume()
    }

    override fun onPause() {
        binding.navigationViewMain.removeHeaderView(naviHeaderBinding.root)
        super.onPause()
    }
}
