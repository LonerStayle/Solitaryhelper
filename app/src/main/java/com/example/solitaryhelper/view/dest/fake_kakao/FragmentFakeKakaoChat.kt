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

        var autoChatDoubleCheckRun = Array(20) { false }


    }


    private var buttonClick = false
    private var inUse = false
    private val soundId by lazy { soundPool.load(requireContext(), R.raw.kakaotalkpool, 1) }


    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setBackButtonListener()
        setMyTestSendButtonClickListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {

        setData()
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
        binding.name = args.name
        viewModelShared; soundPool; soundId

        if (!isResumed) {
            viewModelKaKaoTalk.kakaoData.observe(viewLifecycleOwner, Observer {
                val setDataList: MutableList<KaKaoTalkChatData> = mutableListOf()
                for (i in it.indices) {
                    Log.d("opop8888", "인덱스 갯수체크${it.size - 1}")
                    setDataList.add(
                        KaKaoTalkChatData(
                            it[i].id,
                            it[i].textBoxList,
                            MutableList(args.ListBox.size) { false },
                            it[i].messageArrivalTime!!
                        )
                    )
                }
                viewModelKaKaoChat.listInsert(setDataList)

            })
        }

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
//            delay(Contents.AUTO_CHAT_DEALY)
            delay(5000)
            if (!autoChatRun)
                return

            val shuffleMode = list.shuffled()
//            FragmentFakeKakaoTalk.chatUse = true
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
            if (it.isNullOrEmpty())
                return@Observer


            if (!inUse) {
                Log.d("opop1", "${args.itemIdPosition.toInt()}")
                recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                    args.itemIdPosition.toInt(),
                    args.profileImage,
                    args.name,
                    it
                )

                inUse = true
                return@Observer
            }


            buttonClick = false

        })

        viewModelKaKaoChat.chatListPlus.observe(requireActivity(), Observer {newData->
            //adapter down casting 오류 방지
            if (binding.recyclerViewKaKaoChat.adapter == null && !isResumed) {
                return@Observer
            }
            (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                Log.d("opop9999", "플러스 실행 확인 ")
                val dataList = this.chatList

                val textList = dataList[args.itemIdPosition.toInt()].textList as MutableList
                val user = dataList[args.itemIdPosition.toInt()].user?.toMutableList()
                val timeList = dataList[args.itemIdPosition.toInt()].timeList.toMutableList()

                textList.add(newData.textList); user!!.add(newData.user);timeList.add(newData.timeList)

                val newDataUpdate = KaKaoTalkChatData(
                    textList = textList,
                    user = user,
                    timeList = timeList
                )

                (dataList as MutableList)[args.itemIdPosition.toInt()] = newDataUpdate

                notifyItemChanged(chatList[args.itemIdPosition.toInt()].textList.lastIndex - 1)
                notifyItemInserted(chatList[args.itemIdPosition.toInt()].textList.lastIndex)

                recyclerViewKaKaoChat.scrollToPosition(dataList[args.itemIdPosition.toInt()].timeList.lastIndex)

            }
        })
    }


    override fun onPause() {
        inUse = false
        viewModelKaKaoChat.listInsert(
            (binding.recyclerViewKaKaoChat.adapter as
                    AdapterRecyclerViewKaKaoChat).chatList
        )
        super.onPause()
    }
}

