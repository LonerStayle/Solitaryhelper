package com.example.solitaryhelper.view.dest.fake_kakao

import androidx.lifecycle.Observer
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeKakaoChatBinding
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewKaKaoChat
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents

import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.viewmodel.SharedViewModel
import kotlinx.coroutines.*

var test0 = 0

class FragmentFakeKakaoChat :
    BaseFragment<FragmentFakeKakaoChatBinding>(R.layout.fragment_fake_kakao_chat) {
    private val args by lazy {
        FragmentFakeKakaoChatArgs.fromBundle(
            requireArguments()
        )
    }

    companion object{

        var positionCheckList: Array<SharedViewModel.ZeroPositionCheck>? = null
        var positionSendRunCheck: Boolean? = null
        var autoChatDoubleCheckRun = Array<Boolean>(20){false}
    }



    private val chatDataList = mutableListOf<KaKaoTalkChatData>()

    private var buttonClick = false



    override fun FragmentFakeKakaoChatBinding.setEventListener() {
        setButtonClickListener()
    }

    override fun FragmentFakeKakaoChatBinding.setCreateView() {
        //화면전환 후에도 사용이 지속되기 위한 빠른 초기화
        viewModelShared

        setPositionCheckData()
        setChatDataList()
        setDefaultAdapter()
        setRecyclerView()
        setRunAutoChatSetting()
    }

    override fun FragmentFakeKakaoChatBinding.setLiveDataInObserver() {
        setObserver()
    }


    private fun FragmentFakeKakaoChatBinding.setRecyclerView() {
        recyclerViewKaKaoChat.setHasFixedSize(true)
    }

    private fun setPositionCheckData() {
        if (positionCheckList?.size == 0) {
            for (i in 0..19) {
                positionCheckList!![i] = SharedViewModel.ZeroPositionCheck(
                    i, false
                )

                if (i == 0)
                    positionCheckList!![0] = SharedViewModel.ZeroPositionCheck(
                        0, true
                    )
            }
        } else
            return
    }

    private fun FragmentFakeKakaoChatBinding.setChatDataList() {


        if (!operationByPosition()) {
            positionCheckList = Array(20) { SharedViewModel.ZeroPositionCheck(0, false) }



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
                when (args.itemIdPosition) {


                    0L -> viewModelKaKaoChat.insertAllList(this.chatList)
                    1L -> viewModelKaKaoChat.insertAllList2(this.chatList)
                    2L -> viewModelKaKaoChat.insertAllList3(this.chatList)
                    3L -> viewModelKaKaoChat.insertAllList4(this.chatList)
                    4L -> viewModelKaKaoChat.insertAllList5(this.chatList)
                    5L -> viewModelKaKaoChat.insertAllList6(this.chatList)
                    6L -> viewModelKaKaoChat.insertAllList7(this.chatList)
                    7L -> viewModelKaKaoChat.insertAllList8(this.chatList)
                    8L -> viewModelKaKaoChat.insertAllList9(this.chatList)
                    9L -> viewModelKaKaoChat.insertAllList10(this.chatList)
                    10L -> viewModelKaKaoChat.insertAllList11(this.chatList)
                    11L -> viewModelKaKaoChat.insertAllList12(this.chatList)
                    12L -> viewModelKaKaoChat.insertAllList13(this.chatList)
                    13L -> viewModelKaKaoChat.insertAllList14(this.chatList)
                    14L -> viewModelKaKaoChat.insertAllList15(this.chatList)
                    15L -> viewModelKaKaoChat.insertAllList16(this.chatList)
                    16L -> viewModelKaKaoChat.insertAllList17(this.chatList)
                    17L -> viewModelKaKaoChat.insertAllList18(this.chatList)
                    18L -> viewModelKaKaoChat.insertAllList19(this.chatList)
                    19L -> viewModelKaKaoChat.insertAllList20(this.chatList)
                }
            }
            when (args.itemIdPosition) {
                0L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun =
                    true
                1L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2 =
                    true
                2L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3 =
                    true
                3L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4 =
                    true
                4L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5 =
                    true
                5L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6 =
                    true
                6L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7 =
                    true
                7L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8 =
                    true
                8L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9 =
                    true
                9L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10 =
                    true
                10L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11 =
                    true
                11L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12 =
                    true
                12L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13 =
                    true
                13L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14 =
                    true
                14L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15 =
                    true
                15L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16 =
                    true
                16L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17 =
                    true
                17L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18 =
                    true
                18L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19 =
                    true
                19L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20 =
                    true
            }

        } else
            return
    }

    private fun operationByPosition(): Boolean {

        return when (args.itemIdPosition) {
            0L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
            1L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun2
            2L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun3
            3L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun4
            4L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun5
            5L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun6
            6L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun7
            7L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun8
            8L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun9
            9L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun10
            10L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun11
            11L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun12
            12L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun13
            13L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun14
            14L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun15
            15L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun16
            16L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun17
            17L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun18
            18L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun19
            19L -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun20
            else -> PrefCheckRun.getInstance(requireContext()).kakaoChatObserverControlFirstRun
        }
    }

    private fun FragmentFakeKakaoChatBinding.setButtonClickListener() {

        buttonSend.setOnClickListener {
            buttonClick = true
            when (args.itemIdPosition) {


                0L -> {
                    viewModelKaKaoChat.insertItemAdd(

                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )

                    )


                }
                1L -> {
                    viewModelKaKaoChat.insertItemAdd2(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                2L -> {
                    viewModelKaKaoChat.insertItemAdd3(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                3L -> {
                    viewModelKaKaoChat.insertItemAdd4(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                4L -> {
                    viewModelKaKaoChat.insertItemAdd5(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                5L -> {
                    viewModelKaKaoChat.insertItemAdd6(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                6L -> {
                    viewModelKaKaoChat.insertItemAdd7(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                7L -> {
                    viewModelKaKaoChat.insertItemAdd8(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                8L -> {
                    viewModelKaKaoChat.insertItemAdd9(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                9L -> {
                    viewModelKaKaoChat.insertItemAdd10(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                10L -> {
                    viewModelKaKaoChat.insertItemAdd11(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                11L -> {
                    viewModelKaKaoChat.insertItemAdd12(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                12L -> {
                    viewModelKaKaoChat.insertItemAdd13(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                13L -> {
                    viewModelKaKaoChat.insertItemAdd14(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                14L -> {
                    viewModelKaKaoChat.insertItemAdd15(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                15L -> {
                    viewModelKaKaoChat.insertItemAdd16(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                16L -> {
                    viewModelKaKaoChat.insertItemAdd17(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                17L -> {
                    viewModelKaKaoChat.insertItemAdd18(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                18L -> {
                    viewModelKaKaoChat.insertItemAdd19(
                        KaKaoTalkChatData(
                            textList = editTextTalkBox.text.toString(),
                            user = true
                        )
                    )
                }
                19L -> {
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

    private fun setRunAutoChatSetting() {
        fun setAutoChat() {
            CoroutineScope(Dispatchers.Main ).launch {
                setCoroutine() }
        }

        for(i in 0..19){
            when {
               args.itemIdPosition.toInt()==i && !autoChatDoubleCheckRun[i] -> setAutoChat()
            }
        }
    }

    private suspend fun setCoroutine(){
        while (true) {

            when (args.itemIdPosition) {

                0L -> {
                    autoChatDoubleCheckRun[0] = true
                    delay(5000)
                        viewModelShared.sendToPosition(0)

                        positionSendRunCheck = true
                        viewModelKaKaoChat.insertItemAdd(
                            KaKaoTalkChatData(
                                textList = "1-${++test0}",
                                user = false
                            )
                        )


                }
                1L -> {
                    autoChatDoubleCheckRun[1] = true
                        delay(10000)
                        viewModelShared.sendToPosition(1)

                        positionSendRunCheck = true
                        viewModelKaKaoChat.insertItemAdd2(
                            KaKaoTalkChatData(
                                textList = "2-${++test0}",
                                user = false
                            )
                        )

                }
                2L -> {
                    autoChatDoubleCheckRun[2] = true
                    delay(15000)
                    viewModelShared.sendToPosition(2)

                    positionSendRunCheck = true
                    viewModelKaKaoChat.insertItemAdd3(
                        KaKaoTalkChatData(
                            textList = "3-${++test0}",
                            user = false
                        )
                    )

                }
                3L -> {
                    autoChatDoubleCheckRun[3] = true
                    delay(20000)
                    viewModelShared.sendToPosition(3)

                    positionSendRunCheck = true
                    viewModelKaKaoChat.insertItemAdd4(
                        KaKaoTalkChatData(
                            textList = "4-${++test0}",
                            user = false
                        )
                    )

                }
                4L -> {
                    autoChatDoubleCheckRun[4] = true
                    delay(25000)
                    viewModelShared.sendToPosition(4)

                    positionSendRunCheck = true
                    viewModelKaKaoChat.insertItemAdd5(
                        KaKaoTalkChatData(
                            textList = "5-${++test0}",
                            user = false
                        )
                    )

                }

                }

            }

        }



    private fun FragmentFakeKakaoChatBinding.setObserver() {
        when (args.itemIdPosition) {
            0L -> {
                viewModelKaKaoChat.myChatText.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )
                            buttonClick = false

                        }
                    }
                })
            }

            1L -> {
                viewModelKaKaoChat.myChatText2.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false

                        }

                    }
                })
            }
            2L -> {
                viewModelKaKaoChat.myChatText3.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            3L -> {
                viewModelKaKaoChat.myChatText4.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            4L -> {
                viewModelKaKaoChat.myChatText5.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            5L -> {
                viewModelKaKaoChat.myChatText6.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            6L -> {
                viewModelKaKaoChat.myChatText7.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            7L -> {
                viewModelKaKaoChat.myChatText8.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            8L -> {
                viewModelKaKaoChat.myChatText9.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            9L -> {
                viewModelKaKaoChat.myChatText10.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            10L -> {
                viewModelKaKaoChat.myChatText11.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            11L -> {
                viewModelKaKaoChat.myChatText12.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            12L -> {
                viewModelKaKaoChat.myChatText13.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            13L -> {
                viewModelKaKaoChat.myChatText14.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            14L -> {
                viewModelKaKaoChat.myChatText15.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            15L -> {
                viewModelKaKaoChat.myChatText16.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            16L -> {
                viewModelKaKaoChat.myChatText17.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            17L -> {
                viewModelKaKaoChat.myChatText18.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            18L -> {
                viewModelKaKaoChat.myChatText19.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }
            19L -> {
                viewModelKaKaoChat.myChatText20.observe(viewLifecycleOwner, Observer {
                    when {
                        (operationByPosition()) -> {
                            recyclerViewKaKaoChat.adapter = AdapterRecyclerViewKaKaoChat(
                                args.profileImage, args.name, it.toMutableList()
                            )

                            buttonClick = false
                        }

                    }
                })
            }


        }
    }


}
