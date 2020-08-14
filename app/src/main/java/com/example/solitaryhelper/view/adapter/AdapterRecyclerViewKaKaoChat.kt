package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatYourTextviewBinding
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData


class AdapterRecyclerViewKaKaoChat(
    private val kaKaoProfile: String,
    private val kaKaoName: String,
     val chatList: MutableList<KaKaoTalkChatData> = mutableListOf()
) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoChat.ViewHolder>() {

    companion object {
        const val MY_TEXT_SEND = 1
        const val YOUR_TEXT_SEND = 2
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var myTextBinding: ViewholderKakaotalkChatMyTextviewBinding? = null
        var yourTextviewBinding: ViewholderKakaotalkChatYourTextviewBinding? = null

        constructor(binding: ViewholderKakaotalkChatMyTextviewBinding) : super(binding.root) {
            myTextBinding = binding
        }

        constructor(binding: ViewholderKakaotalkChatYourTextviewBinding) : super(binding.root) {
            yourTextviewBinding = binding
        }
    }


    override fun getItemViewType(position: Int): Int {

        return if (chatList[position].user == true ) {
            MY_TEXT_SEND
        } else {
            YOUR_TEXT_SEND
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding


         return if (viewType == MY_TEXT_SEND) {
            binding = DataBindingUtil.inflate<ViewholderKakaotalkChatMyTextviewBinding>(
                inflater, R.layout.viewholder_kakaotalk_chat_my_textview, parent, false
            )
            ViewHolder(binding)
        }else{

            binding = DataBindingUtil.inflate<ViewholderKakaotalkChatYourTextviewBinding>(
                inflater, R.layout.viewholder_kakaotalk_chat_your_textview, parent, false
            )
             ViewHolder(binding)
        }

    }


    override fun getItemCount(): Int = chatList.count()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


            holder.myTextBinding?.apply {
                text = chatList[holder.adapterPosition].textList
            }

            holder.yourTextviewBinding?.apply {
                profile = kaKaoProfile
                name = kaKaoName
                text = chatList[holder.adapterPosition].textList
            }



    }
}


