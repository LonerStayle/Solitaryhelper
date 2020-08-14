package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoChat
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.pref.PrefCheckRun

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }

    private val chatDataList = mutableListOf<KaKaoTalkChatData>()


    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setButtonClickListener()
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        setChatDataList()
        setDefaultAdapter()
        setRecyclerView()
    }

    override fun FragmentFakeKakaoChatBinding.setLiveDataInObserver() {
        viewModelKaKaoChat.myChatText.observe(viewLifecycleOwner, Observer {
            when {

                (PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun) -> {
                    recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                        args.profileImage, args.name, it.toMutableList()
                    )
                }

                (PrefCheckRun.getInstance(requireContext()).kaKaoChatObserverButtonClickEvent) -> {
                    (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                        this.chatList.add(it.last())
                        notifyItemInserted(chatList.lastIndex)
                    }
                    PrefCheckRun.getInstance(requireContext()).kaKaoChatObserverButtonClickEvent =
                        false
                }

            }
        })
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerView() {
        recyclerViewKaKaoChat.setHasFixedSize(true)

    }

    private fun FragmentFakeKakaoChatBinding.setButtonClickListener() {

        buttonSend.setOnClickListener {
            PrefCheckRun.getInstance(requireContext()).kaKaoChatObserverButtonClickEvent = true
            viewModelKaKaoChat.insertNewMyChat(
                KaKaoTalkChatData(
                  textList =   editTextTalkBox.text.toString(),
                   user =  true
                )
            )

        }
    }

    private fun setBackButtonListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this@FragmentFakeKakaoChat) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoChat_to_fragmentFakeKakaoTalk2)
        }

    }


    private fun setChatDataList() {
        if (!PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun) {
            for (i in args.ListBox.indices)
                chatDataList.add(
                    KaKaoTalkChatData(
                        i.toLong(),
                        args.ListBox[i],
                        false
                    )
                )
            binding.recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                args.profileImage,args.name, chatDataList
            )
        } else
            return
    }

    private fun setDefaultAdapter() {
        if (!PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun) {
            viewModelKaKaoChat.insertAllList(chatDataList)
            PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun = true
        }
        else
            return
    }
}