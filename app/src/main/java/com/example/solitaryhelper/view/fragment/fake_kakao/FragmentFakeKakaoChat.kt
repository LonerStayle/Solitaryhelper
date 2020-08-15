package com.example.solitaryhelper.view.fragment.fake_kakao

import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
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

    private var buttonClick = false

    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setButtonClickListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {

        setChatDataList()
        setDefaultAdapter()
        setRecyclerView()
    }

    private fun FragmentFakeKakaoChatBinding.setRecyclerView() {
        recyclerViewKaKaoChat.setHasFixedSize(true)

    }


    private fun FragmentFakeKakaoChatBinding.setChatDataList() {

        if (!operationByPosition()) {

            for (i in args.ListBox.indices) {

                chatDataList.add(
                    KaKaoTalkChatData(
                        args.ListBox[i],
                        false
                    )
                )
            }
            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                args.profileImage, args.name, chatDataList
            )
        } else
            return
    }

    private fun setDefaultAdapter() {
        if (!operationByPosition()) {
            (binding.recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                when (args.position) {
                    0 -> {
                        viewModelKaKaoChat.insertAllList(this.chatList)
                        PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun = true
                    }
                    1 ->{
                        viewModelKaKaoChat.insertAllList2(this.chatList)
                    }
                    2 -> viewModelKaKaoChat.insertAllList3(this.chatList)
                    3 -> viewModelKaKaoChat.insertAllList4(this.chatList)
                    4 -> viewModelKaKaoChat.insertAllList5(this.chatList)
                    5 -> viewModelKaKaoChat.insertAllList6(this.chatList)
                    6 -> viewModelKaKaoChat.insertAllList7(this.chatList)
                    7 -> viewModelKaKaoChat.insertAllList8(this.chatList)
                    8 -> viewModelKaKaoChat.insertAllList9(this.chatList)
                    9 -> viewModelKaKaoChat.insertAllList10(this.chatList)
                    10 -> viewModelKaKaoChat.insertAllList11(this.chatList)
                    11 -> viewModelKaKaoChat.insertAllList12(this.chatList)
                    12 -> viewModelKaKaoChat.insertAllList13(this.chatList)
                    13 -> viewModelKaKaoChat.insertAllList14(this.chatList)
                    14 -> viewModelKaKaoChat.insertAllList15(this.chatList)
                    15 -> viewModelKaKaoChat.insertAllList16(this.chatList)
                    16 -> viewModelKaKaoChat.insertAllList17(this.chatList)
                    17 -> viewModelKaKaoChat.insertAllList18(this.chatList)
                    18 -> viewModelKaKaoChat.insertAllList19(this.chatList)
                    19 -> viewModelKaKaoChat.insertAllList20(this.chatList)
                }
            }
            when(args.position){

                1-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2 = true
                2-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3 = true
                3-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4 = true
                4-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5 = true
                5-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6 = true
                6-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7 = true
                7-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8 = true
                8-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9 = true
                9-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10 = true
                10-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11 = true
                11-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12 = true
                12-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13 = true
                13-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14 = true
                14-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15 = true
                15-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16 = true
                16-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17 = true
                17-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18 = true
                18-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19 = true
                19-> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20 = true
            }

        } else
            return
    }

    private fun operationByPosition(): Boolean {

        return when (args.position) {
            0 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
            1 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2
            2 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3
            3 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4
            4 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5
            5 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6
            6 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7
            7 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8
            8 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9
            9 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10
            10 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11
            11 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12
            12 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13
            13 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14
            14 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15
            15 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16
            16 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17
            17 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18
            18 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19
            19 -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20
            else -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
        }
    }

    private fun FragmentFakeKakaoChatBinding.setButtonClickListener() {

        buttonSend.setOnClickListener {
            buttonClick = true
            when (args.position) {


                0 -> {
                    viewModelKaKaoChat.insertItemAdd(

                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                1 -> {
                    viewModelKaKaoChat.insertItemAdd2(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                2 -> {
                    viewModelKaKaoChat.insertItemAdd3(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                3 -> {
                    viewModelKaKaoChat.insertItemAdd4(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                4 -> {
                    viewModelKaKaoChat.insertItemAdd5(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                5 -> {
                    viewModelKaKaoChat.insertItemAdd6(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                6 -> {
                    viewModelKaKaoChat.insertItemAdd7(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                7 -> {
                    viewModelKaKaoChat.insertItemAdd8(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                8 -> {
                    viewModelKaKaoChat.insertItemAdd9(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                9 -> {
                    viewModelKaKaoChat.insertItemAdd10(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                10 -> {
                    viewModelKaKaoChat.insertItemAdd11(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                11 -> {
                    viewModelKaKaoChat.insertItemAdd12(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                12 -> {
                    viewModelKaKaoChat.insertItemAdd13(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                13 -> {
                    viewModelKaKaoChat.insertItemAdd14(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                14 -> {
                    viewModelKaKaoChat.insertItemAdd15(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                15 -> {
                    viewModelKaKaoChat.insertItemAdd16(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                16 -> {
                    viewModelKaKaoChat.insertItemAdd17(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                17 -> {
                    viewModelKaKaoChat.insertItemAdd18(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                18 -> {
                    viewModelKaKaoChat.insertItemAdd19(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                19 -> {
                    viewModelKaKaoChat.insertItemAdd20(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }

            }
        }
    }

    override fun FragmentFakeKakaoChatBinding.setLiveDataInObserver() {
        when (args.position) {
            0 -> {
                viewModelKaKaoChat.myChatText.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition() && !buttonClick) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick ) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            1 -> {
                viewModelKaKaoChat.myChatText2.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            2 -> {
                viewModelKaKaoChat.myChatText3.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            3 -> {
                viewModelKaKaoChat.myChatText4.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            4 -> {
                viewModelKaKaoChat.myChatText5.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            5 -> {
                viewModelKaKaoChat.myChatText6.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            6 -> {
                viewModelKaKaoChat.myChatText7.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            7 -> {
                viewModelKaKaoChat.myChatText8.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            8 -> {
                viewModelKaKaoChat.myChatText9.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            9 -> {
                viewModelKaKaoChat.myChatText10.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            10 -> {
                viewModelKaKaoChat.myChatText11.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            11 -> {
                viewModelKaKaoChat.myChatText12.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            12 -> {
                viewModelKaKaoChat.myChatText13.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            13 -> {
                viewModelKaKaoChat.myChatText14.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            14 -> {
                viewModelKaKaoChat.myChatText15.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            15 -> {
                viewModelKaKaoChat.myChatText16.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            16 -> {
                viewModelKaKaoChat.myChatText17.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            17 -> {
                viewModelKaKaoChat.myChatText18.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            18 -> {
                viewModelKaKaoChat.myChatText19.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }
            19 -> {
                viewModelKaKaoChat.myChatText20.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                        }

                        (buttonClick) -> {
                            (recyclerViewKaKaoChat.adapter as AdapterRecyclerViewKaKaoChat).apply {
                                this.chatList.add(it.last())
                                notifyItemInserted(chatList.lastIndex)
                            }
                            buttonClick = false
                        }
                    }
                })
            }

        }
    }
}
