package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatYourTextviewBinding
import com.example.solitaryhelper.view.dataclass.KaKaoTalkChatData
import com.example.solitaryhelper.view.utill.timeDisplay


class AdapterRecyclerViewKaKaoChat(
    var kaKaoProfile: String,
    var kaKaoName: String,
    var chatList: MutableList<KaKaoTalkChatData> = mutableListOf()

) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoChat.ViewHolder>() {

    companion object {
        const val MY_TEXT_SEND = 1
        const val YOUR_TEXT_SEND = 2
    }

   private var profileVisibleList= MutableList<Int> (chatList.size) {View.VISIBLE}
   private var timeVisibleList= MutableList<Int>  (chatList.size) {View.VISIBLE}

    inner class ViewHolder : RecyclerView.ViewHolder {
        var myTextBinding: ViewholderKakaotalkChatMyTextviewBinding? = null
        var yourTextviewBinding: ViewholderKakaotalkChatYourTextviewBinding? = null

        constructor(binding: ViewholderKakaotalkChatMyTextviewBinding) : super(binding.root) {
            myTextBinding = binding
        }

        constructor(binding: ViewholderKakaotalkChatYourTextviewBinding) : super(binding.root) {
            yourTextviewBinding = binding
        }


        fun setTimeVisible() {


            for (i in 1 until chatList.size) {
                if (i != 0) {
                    if ((chatList[i].timeList == chatList[i - 1].timeList) &&
                        chatList[i].timeList == chatList[i - 1].timeList
                    ) {
                        timeVisibleList[i - 1] = View.INVISIBLE
                        timeVisibleList[i] = View.VISIBLE
                    } else
                        timeVisibleList[i] = View.VISIBLE
                }
            }
        }

        fun setProfileVisible() {

            for (i in 1 until chatList.size) {
                if (chatList[i].user != chatList[i - 1].user||
                    chatList[i].timeList != chatList[i-1].timeList)

                    profileVisibleList[i] = View.VISIBLE
                else
                    profileVisibleList[i] = View.INVISIBLE
            }
        }
    }


    override fun getItemViewType(position: Int): Int {

        return if (chatList[position].user == true) {
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


    override fun getItemCount(): Int = chatList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setProfileVisible()
        holder.setTimeVisible()

        holder.myTextBinding?.apply {
            text = chatList[holder.adapterPosition].textList
            time = timeDisplay(chatList[holder.adapterPosition].timeList)
        }

        holder.yourTextviewBinding?.apply {
            profile = kaKaoProfile
            name = kaKaoName
            text = chatList[holder.adapterPosition].textList
            time = timeDisplay(chatList[holder.adapterPosition].timeList)
            profileVisible = profileVisibleList[holder.adapterPosition]
            timeVisible = timeVisibleList[holder.adapterPosition]

        }

    }

}




