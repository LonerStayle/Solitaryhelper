package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderChatbotBinding
import com.example.solitaryhelper.databinding.ViewholderChatbotMyTextBinding

import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.view.dest.main.tapfragments.FragmentChatBot

class AdapterRecyclerViewChatBot(
    var textList:MutableList<FragmentChatBot.ChatListControl>
) : RecyclerView.Adapter<AdapterRecyclerViewChatBot.ViewHolder>() {

    companion object {
        const val CHAT_BOT = 0
        const val MY_TEXT = 1
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var chatbotBinding: ViewholderChatbotBinding? = null
        var myTextBinding: ViewholderChatbotMyTextBinding? = null

        constructor(binding: ViewholderChatbotBinding) : super(binding.root) {
            chatbotBinding = binding
        }

        constructor(binding: ViewholderChatbotMyTextBinding) : super(binding.root) {
            myTextBinding = binding
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(textList[position].check)
            MY_TEXT
        else
            CHAT_BOT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ViewDataBinding
        return if (viewType == MY_TEXT) {
            binding = DataBindingUtil.inflate<ViewholderChatbotMyTextBinding>(
                LayoutInflater.from(parent.context),R.layout.viewholder_chatbot_my_text,parent,false)
            ViewHolder(binding)
        } else {
            binding = DataBindingUtil.inflate<ViewholderChatbotBinding>(
                LayoutInflater.from(parent.context),R.layout.viewholder_chatbot,parent,false)
            ViewHolder(binding)
        }

    }

    override fun getItemCount(): Int = textList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.myTextBinding?.apply {
            mySelect = textList[position].text
        }

        holder.chatbotBinding?.apply {
            chatBotText = textList[position].text
        }
    }
}