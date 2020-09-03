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
                val textList = this.chatList.textList as MutableList
                val user = this.chatList.user?.toMutableList()
                val timeList = this.chatList.timeList.toMutableList()
                textList.add(it.textList); user!!.add(it.user);timeList.add(it.timeList)

                val newData = KaKaoTalkChatData(
                    textList = textList,
                    user = user,
                    timeList = timeList
                )
                this.chatList = newData

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

