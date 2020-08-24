package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatMyTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderKakaotalkChatYourTextviewBinding
import com.example.solitaryhelper.databinding.ViewholderSmsCustomMytextBinding
import com.example.solitaryhelper.databinding.ViewholderSmsCustomYourtextBinding
import com.example.solitaryhelper.localdb.entitiy.Sms

class AdapterRecyclerViewSmsCustom(var smsList: MutableList<Sms>) :
    RecyclerView.Adapter<AdapterRecyclerViewSmsCustom.ViewHolder>() {

    companion object {
        const val MY_TEXT_SEND = 1
        const val YOUR_TEXT_SEND = 2
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var myTextBinding: ViewholderSmsCustomMytextBinding? = null
        var yourTextviewBinding: ViewholderSmsCustomYourtextBinding? = null

        constructor(binding: ViewholderSmsCustomMytextBinding) : super(binding.root) {
            myTextBinding = binding
        }

        constructor(binding: ViewholderSmsCustomYourtextBinding) : super(binding.root) {
            yourTextviewBinding = binding
        }
    }
    override fun getItemViewType(position: Int): Int {

        return if (smsList[position].mytext) {
            MY_TEXT_SEND
        } else {
            YOUR_TEXT_SEND
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding


        return if (viewType == MY_TEXT_SEND) {
            binding = DataBindingUtil.inflate<ViewholderSmsCustomMytextBinding>(
                inflater, R.layout.viewholder_sms_custom_mytext, parent, false
            )
            ViewHolder(binding)
        } else {

            binding = DataBindingUtil.inflate<ViewholderSmsCustomYourtextBinding>(
                inflater, R.layout.viewholder_sms_custom_yourtext, parent, false
            )
            ViewHolder(binding)
        }
    }

    override fun getItemCount(): Int = smsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myTextBinding?.apply {
            text = smsList[position].textMessage
            time = smsList[position].timeBar
        }
        holder.yourTextviewBinding?.apply {
            text = smsList[position].textMessage
            time = smsList[position].timeBar
        }
    }
}