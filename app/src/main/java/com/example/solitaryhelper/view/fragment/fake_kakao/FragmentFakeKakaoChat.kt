package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoChat
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.pref.PrefUserProfile
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_fake_kakao_chat.*

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }

    private val list = mutableListOf<KaKaoTalkChatData>()


    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setButtonClickListener()
        setBackButtonListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        setData()
        setRecyclerView()
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerView() {
        recyclerViewKaKaoChat.setHasFixedSize(true)
        recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
            args.profileImage, args.name, list
        )
    }

    private fun FragmentFakeKakaoChatBinding.setButtonClickListener() {
        buttonSend.setOnClickListener {

            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                this.myChatList.add(KaKaoTalkChatData(true,editTextTalkBox.text.toString()))
                notifyItemInserted(myChatList.lastIndex)

            }

        }
    }

    private fun setBackButtonListener() {

        requireActivity().onBackPressedDispatcher.addCallback(this@FragmentFakeKakaoChat) {
            findNavController().navigate(R.id.action_fragmentFakeKakaoChat_to_fragmentFakeKakaoTalk2)
        }

    }

    private fun setData() {
        for (i in args.ListBox.indices)
            list.add(KaKaoTalkChatData(false, args.ListBox[i]))
    }
}