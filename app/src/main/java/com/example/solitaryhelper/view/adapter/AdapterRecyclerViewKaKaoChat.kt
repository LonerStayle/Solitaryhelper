package com.example.solitaryhelper.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatYourTextviewBinding
import com.example.solitaryhelper.view.pref.PrefCheckRun

class AdapterRecyclerViewKaKaoChat(
    private val kakaoProfile: String,
    private val kakaoName: String,
    val myChatList: MutableList<String> = mutableListOf(),
    private val context: Context

) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoChat.ViewHolder>() {

    companion object {
        const val MY_TEXT_SEND = 1
        const val YOUR_TEXT_SEND = 2
    }

    private var viewType: Int? = null

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


        val myTextBinding
                = DataBindingUtil.bind<ViewholderKakaotalkChatMyTextviewBinding>(view)


        val yourTextBinding =
            DataBindingUtil.bind<ViewholderKakaotalkChatYourTextviewBinding>(view)


    }

    override fun getItemViewType(position: Int): Int {

        return if (PrefCheckRun.getInstance(context).kaKaoTalkMessageSend) {
            viewType = MY_TEXT_SEND
            MY_TEXT_SEND

        } else {
            viewType = YOUR_TEXT_SEND
            YOUR_TEXT_SEND
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_kakaotalk_chat_your_textview,
            parent, false
        )
        if (viewType == MY_TEXT_SEND) {

            view = LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_kakaotalk_chat_my_textview,
                parent, false
            )
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = myChatList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (viewType == MY_TEXT_SEND) {
            holder.myTextBinding?.apply {
                text = myChatList[holder.adapterPosition]
            }
            return
        }

        holder.yourTextBinding?.apply {
            profile = kakaoProfile
            name = kakaoName
            text = myChatList[holder.adapterPosition]
        }
    }
}