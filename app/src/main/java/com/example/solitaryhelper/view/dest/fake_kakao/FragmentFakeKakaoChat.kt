package com.example.solitaryhelper.view.dest.fake_kakao

import android.app.NotificationManager
import android.content.Context
import android.media.SoundPool
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.database.localdb.entitiy.*
import com.example.solitaryhelper.view.activity.autoChatRun
import com.example.solitaryhelper.view.adapter.kakao_chat.*
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun

import com.example.solitaryhelper.view.utill.keyBoardShowHiding
import com.example.solitaryhelper.viewmodel.KaKaoChatViewModel
import com.example.solitaryhelper.viewmodel.SharedViewModel
import kotlinx.coroutines.*
import java.util.*

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
    private val viewModelKaKaoChat by viewModels<KaKaoChatViewModel> { viewModelFactory }
    private val viewModelShared by lazy{
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val chatBotText by lazy { resources.getStringArray(R.array.chatTalk) }
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }
    private val deepLinkUseCheck by lazy {
        requireArguments().getBoolean("deepLinkUse", false)
    }
    private val soundPool by lazy {
        SoundPool.Builder()
            .build()
    }

    companion object {
        const val NOTIFICATION_ID = 1001
        const val NOTIFICATION_ID_2 = 1002

        var autoChatDoubleCheckRun = Array(20) { false }
        private var inUse = false

    }

    private val dp by lazy { resources.displayMetrics.density }
    private var buttonClick = false

    private val soundId by lazy { soundPool.load(requireContext(), R.raw.kakaotalkpool, 1) }


    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setBackButtonListener()
        setMyTestSendButtonClickListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        Log.d("onCreateViewRestart", "$deepLinkUseCheck")
        setData()
        setBackButtonSetting()
        setButtonUiControl()
        setRecyclerViewSetting()
        setRunAutoChatSetting()
    }


    override fun FragmentFakeKakaoChatBinding.setLiveDataInObserver() {
        setTotalScore()
        setAdapter()
    }

    private fun FragmentFakeKakaoChatBinding.setButtonUiControl() {

        editTextTalkBox.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty())
                    setButtonSendChange(false)
                else
                    setButtonSendChange(true)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setButtonSendChange(true)
            }
        })
    }

    private fun FragmentFakeKakaoChatBinding.setButtonSendChange(textChange: Boolean) {
        if (!textChange) {
            buttonSend.apply {
                setBackgroundResource(0)
                setImageResource(R.drawable.kakao_chat_bottom0)
                setPadding(0, 0, 0, 0)
                layoutParams.height = (26 * dp).toInt()
                layoutParams.width = (24 * dp).toInt()
                buttonSend.isEnabled = false
            }
        } else {
            buttonSend.apply {
                setImageResource(R.drawable.ic_baseline_arrow_upward_24)
                setPadding(2, 2, 2, 2)
                setBackgroundResource(R.drawable.kakao_chat_button)
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                buttonSend.isEnabled = true
            }
        }
    }

    private fun FragmentFakeKakaoChatBinding.setTotalScore() {
        viewModelShared.kaKaoChatTotalNotificationScore.observe(viewLifecycleOwner, Observer {
            toTalScore = it.toString()
        })
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerViewSetting() {
        recyclerViewKaKaoChat.adapter?.notifyDataSetChanged()
        (recyclerViewKaKaoChat.adapter as? AdapterRecyclerViewKaKaoChat)?.setHasStableIds(true)
        recyclerViewKaKaoChat.setHasFixedSize(true)
        recyclerViewKaKaoChat.disableItemAnimator()
        recyclerViewKaKaoChat.setItemViewCacheSize(12)
    }

    private fun setData() {
        autoChatRun = true
        binding.name = args.name
        viewModelShared; soundPool; soundId
    }

    private fun setUpFirstRunViewModel() {

        when (args.itemIdPosition) {
            0L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert(
                        KaKaoTalkChatData(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            1L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert1(
                        KaKaoTalkChatData1(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            2L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert2(
                        KaKaoTalkChatData2(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            3L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert3(
                        KaKaoTalkChatData3(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            4L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert4(
                        KaKaoTalkChatData4(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            5L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert5(
                        KaKaoTalkChatData5(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            6L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert6(
                        KaKaoTalkChatData6(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            7L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert7(
                        KaKaoTalkChatData7(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            8L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert8(
                        KaKaoTalkChatData8(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            9L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert9(
                        KaKaoTalkChatData9(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            10L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert10(
                        KaKaoTalkChatData10(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            11L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert11(
                        KaKaoTalkChatData11(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            12L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert12(
                        KaKaoTalkChatData12(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            13L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert13(
                        KaKaoTalkChatData13(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            14L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert14(
                        KaKaoTalkChatData14(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            15L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert15(
                        KaKaoTalkChatData15(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            16L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert16(
                        KaKaoTalkChatData16(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            17L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert17(
                        KaKaoTalkChatData17(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            18L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert18(
                        KaKaoTalkChatData18(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }
            19L -> {
                for (i in args.ListBox.indices) {
                    viewModelKaKaoChat.insert19(
                        KaKaoTalkChatData19(
                            textList = args.ListBox[i],
                            user = false,
                            timeList = args.timeList[i]
                        )
                    )
                }
            }

        }

    }


    private fun FragmentFakeKakaoChatBinding.setMyTestSendButtonClickListener() {

        buttonSend.setOnClickListener {

            keyBoardShowHiding(requireContext(), editTextTalkBox)

            if (TextUtils.isEmpty(editTextTalkBox.text))
                return@setOnClickListener

            buttonClick = true

            viewModelKaKaoChat.insert(
                KaKaoTalkChatData(
                    textList = editTextTalkBox.text.toString(),
                    user = true,
                    timeList = Contents.timePattern.format(Date())
                )
            )

            editTextTalkBox.setText("")
        }
    }

    private fun setRunAutoChatSetting() {

        fun setAutoChat() {
            CoroutineScope(Dispatchers.Main).launch {
                if (autoChatDoubleCheckRun.filter { it }.size <= 5)
                    setCoroutine()
            }
        }

        for (i in 0..19) {
            when {
                args.itemIdPosition.toInt() == i && !autoChatDoubleCheckRun[i] -> setAutoChat()
            }
        }
    }


    private suspend fun setCoroutine() {
        val list = chatBotText.toList()

        val manager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        viewModelKaKaoChat.clearExistingNotifications(0, manager = manager)

// 백그라운드에서 살 수 있도록 초기화
        val context = requireContext()
        requireActivity().baseContext

        while (autoChatRun!!) {
            when (args.itemIdPosition) {
                0L -> {
                    autoChatDoubleCheckRun[0] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert(
                        KaKaoTalkChatData(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )

                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            0,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)

                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                1L -> {
                    autoChatDoubleCheckRun[1] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert1(
                        KaKaoTalkChatData1(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            1,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                2L -> {
                    autoChatDoubleCheckRun[2] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert2(
                        KaKaoTalkChatData2(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            2,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                3L -> {
                    autoChatDoubleCheckRun[3] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert3(
                        KaKaoTalkChatData3(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            3,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                4L -> {
                    autoChatDoubleCheckRun[4] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert4(
                        KaKaoTalkChatData4(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            4,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                5L -> {
                    autoChatDoubleCheckRun[5] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert5(
                        KaKaoTalkChatData5(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            5,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                6L -> {
                    autoChatDoubleCheckRun[6] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert6(
                        KaKaoTalkChatData6(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            6,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                7L -> {
                    autoChatDoubleCheckRun[7] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert7(
                        KaKaoTalkChatData7(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            7,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                8L -> {
                    autoChatDoubleCheckRun[8] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert8(
                        KaKaoTalkChatData8(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            8,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                9L -> {
                    autoChatDoubleCheckRun[9] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert9(
                        KaKaoTalkChatData9(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            9,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                10L -> {
                    autoChatDoubleCheckRun[10] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert10(
                        KaKaoTalkChatData10(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            10,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                11L -> {
                    autoChatDoubleCheckRun[11] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert11(
                        KaKaoTalkChatData11(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            11,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                12L -> {
                    autoChatDoubleCheckRun[12] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert12(
                        KaKaoTalkChatData12(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            12,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                13L -> {
                    autoChatDoubleCheckRun[13] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert13(
                        KaKaoTalkChatData13(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            13,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                14L -> {
                    autoChatDoubleCheckRun[14] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert14(
                        KaKaoTalkChatData14(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            14,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                15L -> {
                    autoChatDoubleCheckRun[15] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert15(
                        KaKaoTalkChatData15(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            15,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                16L -> {
                    autoChatDoubleCheckRun[16] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert16(
                        KaKaoTalkChatData16(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            16,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                17L -> {
                    autoChatDoubleCheckRun[17] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert17(
                        KaKaoTalkChatData17(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            17,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                18L -> {
                    autoChatDoubleCheckRun[18] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert18(
                        KaKaoTalkChatData18(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )


                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            18,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }
                19L -> {
                    autoChatDoubleCheckRun[19] = true
//            delay(Contents.AUTO_CHAT_DEALY)

                    delay(((Math.random()*15000)+5000).toLong())
                    if (!autoChatRun!!)
                        return

                    val shuffleMode = list.shuffled()

                    viewModelKaKaoChat.insert19(
                        KaKaoTalkChatData19(
                            textList = shuffleMode[0],
                            user = false,
                            timeList = Contents.timePattern.format(Date())
                        )
                    )

                    viewModelShared.sendToChanges(
                        SharedViewModel.SendToChange(
                            19,
                            shuffleMode[0],
                            Contents.timePattern.format(Date())
                        )
                    )
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
                    requireArguments().putBoolean("deepLinkUse", true)
                    if (!isResumed) {
                        viewModelKaKaoChat.messagingStyle(
                            R.drawable.newsample2,
                            context,
                            manager,
                            binding.name!!,
                            shuffleMode[0],
                            requireArguments()
                        )

                    }
                }


            }
        }
    }


    private fun FragmentFakeKakaoChatBinding.setBackButtonListener() {
        imageViewBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun setBackButtonSetting() {
        PrefCheckRun.getInstance(requireContext()).kaKaoChatNavDeepLinkUseCheck = deepLinkUseCheck
        if (PrefCheckRun.getInstance(requireContext()).kaKaoChatNavDeepLinkUseCheck) {
            requireActivity().onBackPressedDispatcher.addCallback(this@FragmentFakeKakaoChat) {
                findNavController().navigate(R.id.action_fragmentFakeKakaoChat_to_fragmentAutoChatRunCheck)
            }
        }
    }

    private fun FragmentFakeKakaoChatBinding.setAdapter() {

        when (args.itemIdPosition) {
            0L -> viewModelKaKaoChat.chatList.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer
                if (recyclerViewKaKaoChat.adapter == null) {
                    Log.d("opop888", "전체리스트 추가 확인")
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            1L -> viewModelKaKaoChat.chatList1.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat1(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat1).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            2L -> viewModelKaKaoChat.chatList2.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat2(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat2).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            3L -> viewModelKaKaoChat.chatList3.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat3(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat3).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            4L -> viewModelKaKaoChat.chatList4.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat4(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat4).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            5L -> viewModelKaKaoChat.chatList5.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat5(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat5).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            6L -> viewModelKaKaoChat.chatList6.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat6(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat6).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            7L -> viewModelKaKaoChat.chatList7.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat7(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat7).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            8L -> viewModelKaKaoChat.chatList8.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat8(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat8).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            9L -> viewModelKaKaoChat.chatList9.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat9(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat9).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            10L -> viewModelKaKaoChat.chatList10.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat10(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat10).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            11L -> viewModelKaKaoChat.chatList11.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat11(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat11).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            12L -> viewModelKaKaoChat.chatList12.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat12(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat12).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            13L -> viewModelKaKaoChat.chatList13.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat13(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat13).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            14L -> viewModelKaKaoChat.chatList14.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat14(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat14).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            15L -> viewModelKaKaoChat.chatList15.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat15(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat15).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            16L -> viewModelKaKaoChat.chatList16.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat16(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat16).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            17L -> viewModelKaKaoChat.chatList17.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat17(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat17).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            18L -> viewModelKaKaoChat.chatList18.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat18(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat18).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })
            19L -> viewModelKaKaoChat.chatList19.observe(viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) {
                    setUpFirstRunViewModel()
                    return@Observer
                }

                if (!isResumed)
                    return@Observer

                Log.d("opop888", "전체리스트 추가 확인")
                if (recyclerViewKaKaoChat.adapter == null) {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat19(
                        args.profileImage,
                        args.name,
                        it
                    )
                } else if (recyclerViewKaKaoChat.adapter != null) {
                    Log.d("opop999", "조금씩 추가확인")
                    (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat19).apply {
                        this.chatList = it

                        notifyItemChanged(chatList.lastIndex - 1)
                        notifyItemInserted(chatList.lastIndex)
                        recyclerViewKaKaoChat.scrollToPosition(chatList.lastIndex)
                    }
                }
            })

        }
    }

    override fun onPause() {
        inUse = false
        super.onPause()
    }
}

