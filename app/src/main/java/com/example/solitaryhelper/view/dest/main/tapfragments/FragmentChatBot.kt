package com.example.solitaryhelper.view.dest.main.tapfragments

import android.view.View
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentChatbotBinding
import com.example.solitaryhelper.view.adapter.AdapterRecyclerViewChatBot
import com.example.solitaryhelper.view.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentChatBot : BaseFragment<FragmentChatbotBinding>(R.layout.fragment_chatbot) {

    data class ChatListControl(
        val text: String,
        val check: Boolean = false
    )

    private val chatBotGreeting
            by lazy { resources.getStringArray(R.array.chatBotGreetings) }
    private val chatBotOneSide
            by lazy { resources.getStringArray(R.array.chatBotOneSide) }

    private val chatBotOneSideConversationAnswer
            by lazy { resources.getStringArray(R.array.chatBotOneSideConversationAnswer) }
    private val chatBotTwoSideConversationAnswer
            by lazy { resources.getStringArray(R.array.chatBotTwoSideConversationAnswer) }

    private val chatBotSoloGameAnswer
            by lazy { resources.getStringArray(R.array.chatBotSoloGameAnswer) }

    private val chatBotTwoInOneSide
            by lazy { resources.getStringArray(R.array.chatBotTwoInOneSide) }

    private val chatBotTwoInTwoSide
            by lazy { resources.getStringArray(R.array.chatBotTwoInTwoSide) }

    private val chatBotTwoInThreeSideConversationAnswer
            by lazy { resources.getStringArray(R.array.chatBotTwoInThreeSideConversationAnswer) }

    private val chatBotTwoInThreeSide
            by lazy { resources.getStringArray(R.array.chatBotTwoInThreeSide) }

    private val controlList: MutableList<ChatListControl> = mutableListOf()

    override fun FragmentChatbotBinding.setEventListener() {
        setOneAndOneClickListener()
        setOneAndTwoClickListener()
        setTwoAndOneClickListener()
        setSoloOneClickListener()
        setSoloTwoClickListener()
        setSoloThreeClickListener()
        setSoloFourClickListenr()
        setTwoAndTwoClickListener()
        setTwoAndThreeClickLstener()
        setTwoAndFourClickListener()
        setThreeAndOneToSevenClickListener()
        setThreeAndEightClickListener()
    }


    override fun FragmentChatbotBinding.setCreateView() {
        setText()
        setAdapter()

    }

    private fun FragmentChatbotBinding.setAdapter() {
        CoroutineScope(Dispatchers.Main).launch {
            controlList.add(ChatListControl(chatBotGreeting.toList().shuffled()[0]))
            recyclerViewChatBot.adapter = AdapterRecyclerViewChatBot(controlList)
            delay(3000)
            setOneAnswerVisible()
        }
    }

    private fun FragmentChatbotBinding.setOneAndTwoClickListener() {
        textViewOneAnswerQuestionTwo.setOnClickListener {
            setNextPage(chatBotOneSideConversationAnswer[1]) {
                setTwoAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setText() {
        oneAndOne = chatBotOneSideConversationAnswer[0]
        oneAndTwo = chatBotOneSideConversationAnswer[1]
        twoAndOne = chatBotTwoSideConversationAnswer[0]
        twoAndTwo = chatBotTwoSideConversationAnswer[1]
        twoAndThree = chatBotTwoSideConversationAnswer[2]
        twoAndFour = chatBotTwoSideConversationAnswer[3]
        soloOne = chatBotSoloGameAnswer[0]
        soloTwo = chatBotSoloGameAnswer[1]
        soloThree = chatBotSoloGameAnswer[2]
        soloFour = chatBotSoloGameAnswer[3]
        threeAndOne = chatBotTwoInThreeSideConversationAnswer[0]
        threeAndTwo = chatBotTwoInThreeSideConversationAnswer[1]
        threeAndThree = chatBotTwoInThreeSideConversationAnswer[2]
        threeAndFour = chatBotTwoInThreeSideConversationAnswer[3]
        threeAndFive = chatBotTwoInThreeSideConversationAnswer[4]
        threeAndSix = chatBotTwoInThreeSideConversationAnswer[5]
        threeAndSeven = chatBotTwoInThreeSideConversationAnswer[6]
        threeAndEight = chatBotTwoInThreeSideConversationAnswer[7]
    }


    private fun FragmentChatbotBinding.setOneAnswerVisible() {
        layoutChatBotOneSideConversationAnswer.visibility = View.VISIBLE
        layoutChatBotTwoSideConversationAnswer.visibility = View.GONE
        layoutChatBotThreeSideConversationAnswer.visibility = View.GONE
        layoutSolo.visibility = View.GONE
    }

    private fun FragmentChatbotBinding.setTwoAnswerVisible() {
        layoutChatBotOneSideConversationAnswer.visibility = View.GONE
        layoutChatBotTwoSideConversationAnswer.visibility = View.VISIBLE
        layoutChatBotThreeSideConversationAnswer.visibility = View.GONE
        layoutSolo.visibility = View.GONE
    }

    private fun FragmentChatbotBinding.setThreeAnswerVisible() {
        layoutChatBotOneSideConversationAnswer.visibility = View.GONE
        layoutChatBotTwoSideConversationAnswer.visibility = View.GONE
        layoutChatBotThreeSideConversationAnswer.visibility = View.VISIBLE
        layoutSolo.visibility = View.GONE
    }

    private fun FragmentChatbotBinding.setSoloVisible() {

        layoutChatBotOneSideConversationAnswer.visibility = View.GONE
        layoutChatBotTwoSideConversationAnswer.visibility = View.GONE
        layoutChatBotThreeSideConversationAnswer.visibility = View.GONE
        layoutSolo.visibility = View.VISIBLE
    }

    private fun FragmentChatbotBinding.setAllGONE() {
        layoutChatBotOneSideConversationAnswer.visibility = View.GONE
        layoutChatBotTwoSideConversationAnswer.visibility = View.GONE
        layoutChatBotThreeSideConversationAnswer.visibility = View.GONE
        layoutSolo.visibility = View.GONE
    }

    private fun FragmentChatbotBinding.setOneAndOneClickListener() {
        textViewOneAnswerQuestionOne.setOnClickListener {
            setMyTextInsert(chatBotOneSideConversationAnswer[0], chatBotOneSide[0]) {
                setOneAnswerVisible()
            }
        }
    }


    private fun FragmentChatbotBinding.setTwoAndOneClickListener() {
        textViewTwoAnswerQuestionOne.setOnClickListener {
            setNextPage(chatBotTwoSideConversationAnswer[0]) {
                setSoloVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setThreeAndEightClickListener() {
        textViewThreeAnswerQuestionEight.setOnClickListener {
            setPrevPage {
                setTwoAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setThreeAndOneToSevenClickListener() {
        textViewThreeAnswerQuestionOne.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[0], chatBotTwoInThreeSide[0]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionTwo.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[1], chatBotTwoInThreeSide[1]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionThree.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[2], chatBotTwoInThreeSide[2]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionFour.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[3], chatBotTwoInThreeSide[3]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionFive.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[4], chatBotTwoInThreeSide[4]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionSix.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[5], chatBotTwoInThreeSide[5]) {
                setThreeAnswerVisible()
            }
        }
        textViewThreeAnswerQuestionSeven.setOnClickListener {
            setMyTextInsert(chatBotTwoInThreeSideConversationAnswer[6], chatBotTwoInThreeSide[6]) {
                setThreeAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setTwoAndFourClickListener() {
        textViewTwoAnswerQuestionFour.setOnClickListener {
            setPrevPage {
                setOneAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setTwoAndThreeClickLstener() {
        textViewTwoAnswerQuestionThree.setOnClickListener {
            setNextPage(chatBotTwoInThreeSideConversationAnswer[2]) {
                setThreeAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setTwoAndTwoClickListener() {
        textViewTwoAnswerQuestionTwo.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                controlList.add(ChatListControl(chatBotTwoInThreeSideConversationAnswer[1], true))
                setAllGONE()
                (recyclerViewChatBot.adapter as AdapterRecyclerViewChatBot).apply {
                    this.textList = controlList

                    notifyItemInserted(textList.lastIndex)
                    notifyItemChanged(textList.lastIndex - 1)
                    recyclerViewChatBot.scrollToPosition(0)

                }



                (recyclerViewChatBot.adapter as AdapterRecyclerViewChatBot).apply {

                    CoroutineScope(Dispatchers.Main).launch {
                        textList.add(ChatListControl(chatBotTwoInTwoSide[0]))
                        notifyItemInserted(textList.lastIndex)
                        recyclerViewChatBot.scrollToPosition(controlList.lastIndex)
                        delay(5000)
                        textList.add(ChatListControl(chatBotTwoInTwoSide[1]))
                        notifyItemInserted(textList.lastIndex)
                        recyclerViewChatBot.scrollToPosition(controlList.lastIndex)
                        delay(5000)
                        textList.add(ChatListControl(chatBotTwoInTwoSide[2]))
                        notifyItemInserted(textList.lastIndex)
                        recyclerViewChatBot.scrollToPosition(controlList.lastIndex)
                        delay(5000)
                        textList.add(ChatListControl(chatBotTwoInTwoSide[3]))
                        notifyItemInserted(textList.lastIndex)
                        recyclerViewChatBot.scrollToPosition(controlList.lastIndex)
                        delay(5000)
                        textList.add(ChatListControl(chatBotTwoInTwoSide[4]))
                        notifyItemInserted(textList.lastIndex)
                        recyclerViewChatBot.scrollToPosition(controlList.lastIndex)

                    }


                }

                delay(20000)
                setTwoAnswerVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setSoloFourClickListenr() {
        textViewSoloFour.setOnClickListener {
            setPrevPage {
                setTwoAnswerVisible()
            }

        }
    }

    private fun FragmentChatbotBinding.setSoloThreeClickListener() {
        textViewSoloThree.setOnClickListener {
            setMyTextInsert(chatBotSoloGameAnswer[2], chatBotTwoInOneSide[2]) {
                setSoloVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setSoloTwoClickListener() {
        textViewSoloTwo.setOnClickListener {
            setMyTextInsert(chatBotSoloGameAnswer[1], chatBotTwoInOneSide[1]) {
                setSoloVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setSoloOneClickListener() {
        textViewSoloOne.setOnClickListener {
            setMyTextInsert(chatBotSoloGameAnswer[0], chatBotTwoInOneSide[0]) {
                setSoloVisible()
            }
        }
    }

    private fun FragmentChatbotBinding.setMyTextInsert(
        mychat: String, yourText: String,
        afterFunction: () -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            controlList.add(ChatListControl(mychat, true))
            setAllGONE()
            (recyclerViewChatBot.adapter as AdapterRecyclerViewChatBot).apply {
                this.textList = controlList
                notifyItemInserted(textList.lastIndex)
                notifyItemChanged(textList.lastIndex - 1)
                recyclerViewChatBot.scrollToPosition(textList.lastIndex)
            }
            delay(2000L)
            controlList.add(ChatListControl(yourText))
            (recyclerViewChatBot.adapter as AdapterRecyclerViewChatBot).apply {
                this.textList = controlList
                notifyItemInserted(textList.lastIndex)
                notifyItemChanged(textList.lastIndex - 1)
                recyclerViewChatBot.scrollToPosition(textList.lastIndex)
            }

            delay(2000)
            afterFunction()
        }
    }

    private fun FragmentChatbotBinding.setNextPage(myText: String, afterFunction: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {

            controlList.add(ChatListControl(myText, true))
            setAllGONE()
            (recyclerViewChatBot.adapter as AdapterRecyclerViewChatBot).apply {
                this.textList = controlList
                notifyItemInserted(textList.lastIndex)
                notifyItemChanged(textList.lastIndex - 1)

                recyclerViewChatBot.scrollToPosition(controlList.lastIndex)
            }
            delay(2000)
            afterFunction()
        }

    }

    private fun FragmentChatbotBinding.setPrevPage(afterFunction: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            setAllGONE()
            delay(1000)
            afterFunction()
        }
    }

}