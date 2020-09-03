package com.example.solitaryhelper.view.dest.fake_kakao

import android.app.NotificationManager
import android.content.Context
import android.media.SoundPool
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View

import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkData
import com.example.solitaryhelper.view.activity.autoChatRun
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatDataCopy
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoChat
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun

import com.example.solitaryhelper.view.utill.keyBoardShowHiding
import com.example.solitaryhelper.viewmodel.SharedViewModel
import kotlinx.coroutines.*
import java.util.*

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {

    private val chatbotText by lazy { resources.getStringArray(R.array.chatTalk) }
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }
    private val soundPool by lazy {
        SoundPool.Builder()
            .build()
    }

    companion object {
        const val NOTIFICATION_ID = 1001
        const val NOTIFICATION_ID_2 = 1002
        private var inUse = false
        var positionSendRunCheck: Boolean? = null
        var autoChatDoubleCheckRun = Array(20) { false }

    }


    private var buttonClick = false

    private val soundId by lazy { soundPool.load(requireContext(), R.raw.kakaotalkpool, 1) }


    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setBackButtonListener()
        setMyTestSendButtonClickListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        Log.d("opop7777포지션", "${args.itemIdPosition}")
        Log.d("opop8888텍스트리스트 사이즈", "${args.ListBox}")

        name = args.name
        setData()
        //화면전환 후에도 사용이 지속되기 위한 빠른 초기화
        viewModelShared; soundPool; soundId
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
                if (TextUtils.isEmpty(editTextTalkBox.text)) {
                    buttonSend.setBackgroundResource(0)
                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
                    buttonSend.setPadding(0, 0, 0, 0)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (TextUtils.isEmpty(editTextTalkBox.text)) {
                    buttonSend.setBackgroundResource(0)
                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
                    buttonSend.setPadding(0, 0, 0, 0)

                }
                buttonSend.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
                buttonSend.setPadding(2, 2, 2, 2)
                buttonSend.setBackgroundResource(R.drawable.kakao_chat_button)

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (TextUtils.isEmpty(editTextTalkBox.text)) {
                    buttonSend.setBackgroundResource(0)
                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
                    buttonSend.setPadding(0, 0, 0, 0)

                }
            }

        })
    }

    private fun FragmentFakeKakaoChatBinding.setTotalScore() {
        viewModelShared.kaKaoChatTotalNotificationScore.observe(viewLifecycleOwner, Observer {
            toTalScore = it.toString()
        })
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerViewSetting() {
        (recyclerViewKaKaoChat.adapter as? AdapterRecyclerViewKaKaoChat)?.setHasStableIds(true)
        recyclerViewKaKaoChat.setHasFixedSize(true)
        recyclerViewKaKaoChat.disableItemAnimator()
        recyclerViewKaKaoChat.setItemViewCacheSize(12)


    }

    private fun setData() {
        if (!PrefCheckRun.getInstance(requireContext()).kaKaoTalkChatFirstRunCheck)
            viewModelKaKaoTalk.kakaoData.observe(viewLifecycleOwner, Observer {
                for (i in it.indices) {
                    viewModelKaKaoChat.insert(
                        KaKaoTalkChatData(
                            it[i].id,
                            it[i].textBoxList,
                            MutableList(args.ListBox.size) { false },
                            it[i].messageArrivalTime!!
                        )
                    )
                }
                PrefCheckRun.getInstance(requireContext()).kaKaoTalkChatFirstRunCheck = true
            })


    }


    private fun FragmentFakeKakaoChatBinding.setMyTestSendButtonClickListener() {

        buttonSend.setOnClickListener {


            keyBoardShowHiding(requireContext(), editTextTalkBox)

            if (TextUtils.isEmpty(editTextTalkBox.text))
                return@setOnClickListener

            buttonClick = true


            viewModelKaKaoChat.insertCopyData(
                KaKaoTalkChatDataCopy(
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
                if (args.selectChatRoomCount <= 5)
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
        val list = chatbotText.toList()

        val manager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        viewModelKaKaoChat.clearExistingNotifications(0, manager = manager)
// 백그라운드에서 살 수 있도록 초기화
        val context = requireContext()
        requireActivity().baseContext

        while (autoChatRun) {


            autoChatDoubleCheckRun[args.itemIdPosition.toInt()] = true
            delay(Contents.AUTO_CHAT_DEALY)
            if (!autoChatRun)
                return

            val shuffleMode = list.shuffled()
            positionSendRunCheck = true
            viewModelKaKaoChat.insertCopyData(
                KaKaoTalkChatDataCopy(
                    textList = shuffleMode[0],
                    user = false,
                    timeList = Contents.timePattern.format(Date())
                )
            )


            viewModelShared.sendToChanges(
                SharedViewModel.SendToChange(
                    args.itemIdPosition.toInt(),
                    shuffleMode[0],
                    Contents.timePattern.format(Date())
                )
            )
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)

            if (!isResumed) {
                viewModelKaKaoChat.messagingStyle(
                    R.drawable.newsample2,
                    context,
                    manager,
                    binding.name!!,
                    shuffleMode[0]
                )
            }


        }
    }


    private fun FragmentFakeKakaoChatBinding.setBackButtonListener() {
        imageViewBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        textViewTotalChatScore.setOnClickListener {
            requireActivity().onBackPressed()
        }


    }

    private fun FragmentFakeKakaoChatBinding.setAdapter() {

        viewModelKaKaoChat.chatList.observe(viewLifecycleOwner, Observer {

            if (!inUse && PrefCheckRun.getInstance(requireContext()).kaKaoTalkChatFirstRunCheck) {
                Log.d("opop1", "비었을떄 실행")
                recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                    args.profileImage,
                    args.name,
                    it[args.itemIdPosition.toInt()]
                )

                inUse = true
            } else {
                return@Observer
            }
            recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
            buttonClick = false
        })

        viewModelKaKaoChat.chatListPlus.observe(viewLifecycleOwner, Observer {
            (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {

                this.chatList.textList.toMutableList().add(it.textList)
                this.chatList.user?.toMutableList()?.add(it.user)
                this.chatList.timeList.toMutableList().add(it.timeList)
                notifyItemChanged(chatList.textList.lastIndex - 1)
                notifyItemInserted(chatList.textList.lastIndex)

                viewModelKaKaoChat.insert(this.chatList)
            }
        })
    }

    override fun onPause() {
        inUse = false
        super.onPause()
    }
}


//class FragmentFakeKakaoChat :
//    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
//
//    private val chatbotText by lazy { resources.getStringArray(R.array.chatTalk) }
//    private val args by lazy {
//        FragmentFakeKakaoChatArgs.fromBundle(
//            requireArguments()
//        )
//    }
//    private val soundPool by lazy {
//        SoundPool.Builder()
//            .build()
//    }
//
//    companion object {
//        const val NOTIFICATION_ID = 1001
//        const val NOTIFICATION_ID_2 = 1002
//
//        var positionSendRunCheck: Boolean? = null
//        var autoChatDoubleCheckRun = Array(20) { false }
//
//    }
//
//    private var onCreateViewFirstRun = true
//    private var onCreateViewFirstRun2 = true
//    private var onCreateViewFirstRun3 = true
//    private var onCreateViewFirstRun4 = true
//    private var onCreateViewFirstRun5 = true
//    private var onCreateViewFirstRun6 = true
//    private var onCreateViewFirstRun7 = true
//    private var onCreateViewFirstRun8 = true
//    private var onCreateViewFirstRun9 = true
//    private var onCreateViewFirstRun10 = true
//    private var onCreateViewFirstRun11 = true
//    private var onCreateViewFirstRun12 = true
//    private var onCreateViewFirstRun13 = true
//    private var onCreateViewFirstRun14 = true
//    private var onCreateViewFirstRun15 = true
//    private var onCreateViewFirstRun16 = true
//    private var onCreateViewFirstRun17 = true
//    private var onCreateViewFirstRun18 = true
//    private var onCreateViewFirstRun19 = true
//    private var onCreateViewFirstRun20 = true
//
//
//    private var thisProfileVisibleList: MutableList<Int>? = null
//
//    private var thisTimeVisibleList: MutableList<Int>? = null
//
//    private val chatDataList = mutableListOf<KaKaoTalkChatData>()
//
//    private var buttonClick = false
//
//    private val soundId by lazy { soundPool.load(requireContext(), R.raw.kakaotalkpool, 1) }
//
//
//    override fun FragmentFakeKakaoChatBinding.setEventListener() {
//        setBackButtonListener()
//        setMyTestSendButtonClickListener()
//    }
//
//    override fun FragmentFakeKakaoChatBinding.setCreateView() {
//        name = args.name
//
//        //화면전환 후에도 사용이 지속되기 위한 빠른 초기화
//        viewModelShared; soundPool; soundId
//        setButtonUiControl()
//        setRecyclerViewSetting()
//        setAdapter()
//        setBindValueInAdapter()
//        setRunAutoChatSetting()
//    }
//
//
//    override fun FragmentFakeKakaoChatBinding.setLiveDataInObserver() {
//        setTotalScore()
//        setObserver()
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setButtonUiControl() {
//        editTextTalkBox.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                if (TextUtils.isEmpty(editTextTalkBox.text)) {
//                    buttonSend.setBackgroundResource(0)
//                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
//                    buttonSend.setPadding(0, 0, 0, 0)
//                }
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (TextUtils.isEmpty(editTextTalkBox.text)) {
//                    buttonSend.setBackgroundResource(0)
//                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
//                    buttonSend.setPadding(0, 0, 0, 0)
//
//                }
//                buttonSend.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
//                buttonSend.setPadding(2, 2, 2, 2)
//                buttonSend.setBackgroundResource(R.drawable.kakao_chat_button)
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (TextUtils.isEmpty(editTextTalkBox.text)) {
//                    buttonSend.setBackgroundResource(0)
//                    buttonSend.setImageResource(R.drawable.kakao_chat_bottom0)
//                    buttonSend.setPadding(0, 0, 0, 0)
//
//                }
//            }
//
//        })
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setTotalScore() {
//        viewModelShared.kaKaoChatTotalNotificationScore.observe(viewLifecycleOwner, Observer {
//            toTalScore = it.toString()
//        })
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setRecyclerViewSetting() {
//        (recyclerViewKaKaoChat.adapter as? AdapterRecyclerViewKaKaoChat)?.setHasStableIds(true)
//        recyclerViewKaKaoChat.setHasFixedSize(true)
//        recyclerViewKaKaoChat.disableItemAnimator()
//        recyclerViewKaKaoChat.setItemViewCacheSize(12)
//
//
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setAdapter() {
//
//
//
//        if (!operationByPosition()) {
//
//
//            for (i in args.ListBox.indices) {
//
//                chatDataList.add(
//                    KaKaoTalkChatData(
//                        args.ListBox[i],
//                        false,
//                        args.timeList[i])
//                    )
//
//            }
//
//            thisProfileVisibleList = MutableList(chatDataList.size) { View.VISIBLE }
//            thisTimeVisibleList = MutableList(chatDataList.size) { View.VISIBLE }
//
//
//            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                args.profileImage, args.name, chatDataList
//            )
//
//
//        } else
//            return
//    }
//
//
//    private fun setBindValueInAdapter() {
//        if (!operationByPosition()) {
//            (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                when (args.itemIdPosition) {
//
//                    0L -> {
//                        viewModelKaKaoChat.insertAllList(this.chatList);Log.d("opop3", "전체 리스트 인서트")
//                    }
//                    1L -> viewModelKaKaoChat.insertAllList2(this.chatList)
//                    2L -> viewModelKaKaoChat.insertAllList3(this.chatList)
//                    3L -> viewModelKaKaoChat.insertAllList4(this.chatList)
//                    4L -> viewModelKaKaoChat.insertAllList5(this.chatList)
//                    5L -> viewModelKaKaoChat.insertAllList6(this.chatList)
//                    6L -> viewModelKaKaoChat.insertAllList7(this.chatList)
//                    7L -> viewModelKaKaoChat.insertAllList8(this.chatList)
//                    8L -> viewModelKaKaoChat.insertAllList9(this.chatList)
//                    9L -> viewModelKaKaoChat.insertAllList10(this.chatList)
//                    10L -> viewModelKaKaoChat.insertAllList11(this.chatList)
//                    11L -> viewModelKaKaoChat.insertAllList12(this.chatList)
//                    12L -> viewModelKaKaoChat.insertAllList13(this.chatList)
//                    13L -> viewModelKaKaoChat.insertAllList14(this.chatList)
//                    14L -> viewModelKaKaoChat.insertAllList15(this.chatList)
//                    15L -> viewModelKaKaoChat.insertAllList16(this.chatList)
//                    16L -> viewModelKaKaoChat.insertAllList17(this.chatList)
//                    17L -> viewModelKaKaoChat.insertAllList18(this.chatList)
//                    18L -> viewModelKaKaoChat.insertAllList19(this.chatList)
//                    19L -> viewModelKaKaoChat.insertAllList20(this.chatList)
//                }
//            }
//            setOperationByPositionTrue()
//        } else
//            return
//    }
//
//    private fun setOperationByPositionTrue() {
//        when (args.itemIdPosition) {
//            0L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun =
//                true
//            1L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2 =
//                true
//            2L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3 =
//                true
//            3L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4 =
//                true
//            4L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5 =
//                true
//            5L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6 =
//                true
//            6L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7 =
//                true
//            7L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8 =
//                true
//            8L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9 =
//                true
//            9L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10 =
//                true
//            10L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11 =
//                true
//            11L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12 =
//                true
//            12L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13 =
//                true
//            13L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14 =
//                true
//            14L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15 =
//                true
//            15L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16 =
//                true
//            16L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17 =
//                true
//            17L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18 =
//                true
//            18L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19 =
//                true
//            19L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20 =
//                true
//        }
//    }
//
//    private fun operationByPosition(): Boolean {
//
//        return when (args.itemIdPosition) {
//            0L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
//            1L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2
//            2L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3
//            3L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4
//            4L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5
//            5L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6
//            6L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7
//            7L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8
//            8L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9
//            9L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10
//            10L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11
//            11L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12
//            12L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13
//            13L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14
//            14L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15
//            15L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16
//            16L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17
//            17L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18
//            18L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19
//            19L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20
//            else -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
//        }
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setMyTestSendButtonClickListener() {
//
//        buttonSend.setOnClickListener {
//
//
//            keyBoardShowHiding(requireContext(), editTextTalkBox)
//
//            if (TextUtils.isEmpty(editTextTalkBox.text))
//                return@setOnClickListener
//
//            buttonClick = true
//            when (args.itemIdPosition) {
//
//                0L -> {
//                    viewModelKaKaoChat.insertItemAdd(
//
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//
//                        )
//                    )
//
//
//                }
//                1L -> {
//                    viewModelKaKaoChat.insertItemAdd2(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                2L -> {
//
//                    viewModelKaKaoChat.insertItemAdd3(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                3L -> {
//
//                    viewModelKaKaoChat.insertItemAdd4(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                4L -> {
//
//                    viewModelKaKaoChat.insertItemAdd5(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                5L -> {
//
//                    viewModelKaKaoChat.insertItemAdd6(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                6L -> {
//
//                    viewModelKaKaoChat.insertItemAdd7(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                7L -> {
//
//                    viewModelKaKaoChat.insertItemAdd8(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                8L -> {
//
//                    viewModelKaKaoChat.insertItemAdd9(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                9L -> {
//
//                    viewModelKaKaoChat.insertItemAdd10(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                10L -> {
//
//                    viewModelKaKaoChat.insertItemAdd11(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                11L -> {
//
//                    viewModelKaKaoChat.insertItemAdd12(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                12L -> {
//
//                    viewModelKaKaoChat.insertItemAdd13(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                13L -> {
//
//                    viewModelKaKaoChat.insertItemAdd14(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                14L -> {
//
//                    viewModelKaKaoChat.insertItemAdd15(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                15L -> {
//
//                    viewModelKaKaoChat.insertItemAdd16(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                16L -> {
//
//                    viewModelKaKaoChat.insertItemAdd17(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                17L -> {
//
//                    viewModelKaKaoChat.insertItemAdd18(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                18L -> {
//
//                    viewModelKaKaoChat.insertItemAdd19(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//                19L -> {
//
//                    viewModelKaKaoChat.insertItemAdd20(
//                        KaKaoTalkChatData(
//                            textList =  editTextTalkBox.text.toString(),
//                            user = true,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//
//                }
//
//            }
//            editTextTalkBox.setText("")
//        }
//    }
//
//    private fun setRunAutoChatSetting() {
//
//        fun setAutoChat() {
//            CoroutineScope(Dispatchers.Main).launch {
//                if (args.selectChatRoomCount <= 5)
//                    setCoroutine()
//            }
//        }
//
//        for (i in 0..19) {
//            when {
//                args.itemIdPosition.toInt() == i && !autoChatDoubleCheckRun[i] -> setAutoChat()
//            }
//        }
//    }
//
//
//    private suspend fun setCoroutine() {
//        val list = chatbotText.toList()
//
//        val manager =
//            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        viewModelKaKaoChat.clearExistingNotifications(0, manager = manager)
//// 백그라운드에서 살 수 있도록 초기화
//        val context = requireContext()
//        val activtyContext = requireActivity().baseContext
//
//        while (autoChatRun) {
//
//            when (args.itemIdPosition) {
//
//                0L -> {
//
//                    autoChatDoubleCheckRun[0] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//
//
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            0,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                1L -> {
//
//                    autoChatDoubleCheckRun[1] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd2(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            1,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                2L -> {
//
//                    autoChatDoubleCheckRun[2] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd3(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            2,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                3L -> {
//
//                    autoChatDoubleCheckRun[3] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd4(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            3,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                4L -> {
//
//                    autoChatDoubleCheckRun[4] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd5(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            4,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                5L -> {
//
//                    autoChatDoubleCheckRun[5] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd6(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            5,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                6L -> {
//
//                    autoChatDoubleCheckRun[6] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd7(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            6,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                7L -> {
//
//                    autoChatDoubleCheckRun[7] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd8(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            7,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                8L -> {
//
//                    autoChatDoubleCheckRun[8] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd9(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            8,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                9L -> {
//
//                    autoChatDoubleCheckRun[9] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd10(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            9,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                10L -> {
//
//                    autoChatDoubleCheckRun[10] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd11(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            10,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                11L -> {
//
//                    autoChatDoubleCheckRun[11] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd12(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            11,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                12L -> {
//
//                    autoChatDoubleCheckRun[12] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd13(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            12,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                13L -> {
//
//                    autoChatDoubleCheckRun[13] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd14(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            13,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                14L -> {
//
//                    autoChatDoubleCheckRun[14] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd15(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            14,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                15L -> {
//
//                    autoChatDoubleCheckRun[15] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd16(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            15,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                16L -> {
//
//                    autoChatDoubleCheckRun[16] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd17(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            16,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                17L -> {
//
//                    autoChatDoubleCheckRun[17] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd18(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            17,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                18L -> {
//
//                    autoChatDoubleCheckRun[18] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd19(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            18,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//                19L -> {
//
//                    autoChatDoubleCheckRun[19] = true
//                    delay(Contents.AUTO_CHAT_DEALY)
//                    if (!autoChatRun)
//                        return
//                    val shuffleMode = list.shuffled()
//                    positionSendRunCheck = true
//                    viewModelKaKaoChat.insertItemAdd20(
//                        KaKaoTalkChatData(
//                            textList = shuffleMode[0],
//                            user = false,
//                            timeList = Contents.timePattern.format(Date())
//                        )
//                    )
//                    viewModelShared.sendToChanges(
//                        SharedViewModel.SendToChange(
//                            19,
//                            shuffleMode[0],
//                            Contents.timePattern.format(Date())
//                        )
//                    )
//                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
//
//                    if (!isResumed) {
//                        viewModelKaKaoChat.messagingStyle(
//                            R.drawable.newsample2,
//                            context,
//                            manager,
//                            binding.name!!,
//                            shuffleMode[0],
//                            activtyContext
//                        )
//                    }
//
//                }
//
//
//            }
//        }
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setBackButtonListener() {
//        imageViewBackButton.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//        textViewTotalChatScore.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//
//
//    }
//
//    private fun FragmentFakeKakaoChatBinding.setObserver() {
//        when (args.itemIdPosition) {
//
//            0L -> {
//                viewModelKaKaoChat.myChatText.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            1L -> {
//                viewModelKaKaoChat.myChatText2.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun2 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            2L -> {
//                viewModelKaKaoChat.myChatText3.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun3 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            3L -> {
//                viewModelKaKaoChat.myChatText4.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun4 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            4L -> {
//                viewModelKaKaoChat.myChatText5.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun5 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            5L -> {
//                viewModelKaKaoChat.myChatText6.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun6 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            6L -> {
//                viewModelKaKaoChat.myChatText7.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun7 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            7L -> {
//                viewModelKaKaoChat.myChatText8.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun8 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            8L -> {
//                viewModelKaKaoChat.myChatText9.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun9 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            9L -> {
//                viewModelKaKaoChat.myChatText10.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun10 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            10L -> {
//                viewModelKaKaoChat.myChatText11.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun11 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            11L -> {
//                viewModelKaKaoChat.myChatText12.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun12 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            12L -> {
//                viewModelKaKaoChat.myChatText13.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun13 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            13L -> {
//                viewModelKaKaoChat.myChatText14.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun14 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            14L -> {
//                viewModelKaKaoChat.myChatText15.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun15 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            15L -> {
//                viewModelKaKaoChat.myChatText16.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun16 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            16L -> {
//                viewModelKaKaoChat.myChatText17.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun17 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            17L -> {
//                viewModelKaKaoChat.myChatText18.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun18 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//            18L -> {
//                viewModelKaKaoChat.myChatText19.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun19 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//
//            19L -> {
//                viewModelKaKaoChat.myChatText20.observe(viewLifecycleOwner, Observer {
//
//                    when {
//                        (operationByPosition() && onCreateViewFirstRun) -> {
//
//                            Log.d("opop1", "비었을떄 실행")
//                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
//                                args.profileImage, args.name, it
//                            )
//
//                            onCreateViewFirstRun20 = false
//                        }
//
//                        (operationByPosition() && !onCreateViewFirstRun) -> {
//                            Log.d("opop2", "안 비었을때 실행")
//                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
//                                this.chatList = it
//
//                                notifyItemChanged(chatList.lastIndex - 1)
//                                notifyItemInserted(chatList.lastIndex)
//                            }
//                        }
//                    }
//
//                    recyclerViewKaKaoChat.scrollToPosition(it.lastIndex)
//                    buttonClick = false
//                })
//            }
//
//
//        }
//    }
//}
