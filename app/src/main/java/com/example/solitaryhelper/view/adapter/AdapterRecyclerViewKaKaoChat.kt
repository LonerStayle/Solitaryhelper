package com.example.solitaryhelper.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatYourTextviewBinding
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkData

import com.example.solitaryhelper.view.utill.kakaoTimeDisplay

class AdapterRecyclerViewKaKaoChat(
    var id: Int,
    var kaKaoProfile: String,
    var kaKaoName: String,
    var chatList: List<KaKaoTalkChatData>

) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoChat.ViewHolder>() {

    companion object {
        const val MY_TEXT_SEND = 1
        const val YOUR_TEXT_SEND = 2
    }

    private var profileVisibleList: MutableList<Int>? = null
    private var nameVisibleList: MutableList<Int>? = null
    private var timeVisibleList: MutableList<Int>? = null

     inner class ViewHolder : RecyclerView.ViewHolder {
        var myTextBinding: ViewholderKakaotalkChatMyTextviewBinding? = null
        var yourTextviewBinding: ViewholderKakaotalkChatYourTextviewBinding? = null

        constructor(binding: ViewholderKakaotalkChatMyTextviewBinding) : super(binding.root) {
            myTextBinding = binding
        }

        constructor(binding: ViewholderKakaotalkChatYourTextviewBinding) : super(binding.root) {
            yourTextviewBinding = binding
        }


        fun setTimeVisible(timeVisibleList: MutableList<Int>, chatList: List<KaKaoTalkChatData>) {


            for (i in 1 until chatList[id].textList.size) {

                if ((chatList[id].timeList[i] == chatList[id].timeList[i - 1]) &&
                    chatList[id].user!![i] == chatList[id].user!![i - 1]
                ) {

                    timeVisibleList[i - 1] = View.INVISIBLE
                    timeVisibleList.add(View.VISIBLE)

                } else {
                    timeVisibleList.add(View.VISIBLE)

                }
            }
        }

        fun setProfileVisible(profileVisibleList: MutableList<Int>, chatList: List<KaKaoTalkChatData>) {

            for (i in 1 until chatList[id].textList.size) {
                if (chatList[id].user!![i] != chatList[id].user!![i - 1] ||
                    chatList[id].timeList[i] != chatList[id].timeList[i - 1]
                )

                    profileVisibleList.add(View.VISIBLE)
                else
                    profileVisibleList.add(View.INVISIBLE)
            }
        }

        fun setNameVisible(nameVisibleList: MutableList<Int>, chatList: List<KaKaoTalkChatData>) {

            for (i in 1 until chatList[id].textList.size) {
                if (chatList[id].user!![i] != chatList[id].user!![i - 1] ||
                    chatList[id].timeList[i] != chatList[id].timeList[i - 1]
                )
                    nameVisibleList.add(View.VISIBLE)
                else
                    nameVisibleList.add(View.GONE)
            }
        }


    }


    override fun getItemViewType(position: Int): Int {

        return if (chatList[id].user!![position]) {
            MY_TEXT_SEND
        } else {
            YOUR_TEXT_SEND
        }
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding


        return if (viewType == MY_TEXT_SEND) {
            binding = DataBindingUtil.inflate<ViewholderKakaotalkChatMyTextviewBinding>(
                inflater, R.layout.viewholder_kakaotalk_chat_my_textview, parent, false
            )
            ViewHolder(binding)
        } else {

            binding = DataBindingUtil.inflate<ViewholderKakaotalkChatYourTextviewBinding>(
                inflater, R.layout.viewholder_kakaotalk_chat_your_textview, parent, false
            )
            ViewHolder(binding)
        }


    }


    override fun getItemCount(): Int = chatList[id].textList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        profileVisibleList = MutableList(1) { View.VISIBLE }
        timeVisibleList = MutableList(1) { View.VISIBLE }
        nameVisibleList = MutableList(1) { View.VISIBLE }

//        if (profileVisibleList?.size == 1) {
        holder.setProfileVisible(profileVisibleList!!, chatList)
        holder.setTimeVisible(timeVisibleList!!, chatList)
        holder.setNameVisible(nameVisibleList!!, chatList)
//        }
//        else {
//            holder.setProfileVisibleToLastIndex(profileVisibleList!!, chatList.lastIndex)
//            holder.setTimeVisibleToLastIndex(timeVisibleList!!, chatList.lastIndex)
//            holder.setNameVisibleToLastIndex(nameVisibleList!!, chatList.lastIndex)
//        }

        holder.myTextBinding?.apply {

            text = chatList[id].textList[holder.adapterPosition]
            time = kakaoTimeDisplay(chatList[id].timeList[holder.adapterPosition])
        }

        holder.yourTextviewBinding?.apply {

            text = chatList[id].textList[holder.adapterPosition]
            time = kakaoTimeDisplay(chatList[id].timeList[holder.adapterPosition])

            // 비지블 설정
            nameVisible = nameVisibleList!![holder.adapterPosition]
            profileImageVisible = profileVisibleList!![holder.adapterPosition]
            timeVisible = timeVisibleList!![holder.adapterPosition]

            // 이름과 이미지
            imageViewImageProfile.setImageURI(Uri.parse(kaKaoProfile))
            name = kaKaoName

        }

    }


}
