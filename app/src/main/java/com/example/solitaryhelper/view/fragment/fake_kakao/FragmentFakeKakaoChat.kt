package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.navigation.fragment.navArgs
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoChat
import com.example.solitaryhelper.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_fake_kakao_chat.*

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }

    override fun FragmentFakeKakaoChatBinding.setEventListener() {

    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        setRecyclerView()
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerView() {
        val textList = resources.getStringArray(R.array.sample_list)
        recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
            args.profileImage, args.name, textList.toList(),
            requireContext()
        )
    }
}