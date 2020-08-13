package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkTalkBinding

import com.example.solitaryhelper.localdb.data.KaKaoTalkData
import kotlin.random.Random

class AdapterRecyclerViewKaKaoTalk(
    val kaKaoDataList: List<KaKaoTalkData> = listOf(),
    val clickEvent: (Int) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoTalk.ViewHolder>() {
    private val visibleSettingList = mutableListOf<Int>()

    var i = 0


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ViewholderKakaotalkTalkBinding>(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_kakaotalk_talk,
                parent, false
            )
        )

    override fun getItemCount(): Int = kaKaoDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        while (i < kaKaoDataList.size) {
            fun random(): Int {
                return when (Random.nextInt(2)) {
                    0 -> View.GONE
                    1 -> View.VISIBLE
                    else -> View.GONE
                }
            }
            i++
            visibleSettingList.add(random())
        }


        holder.binding?.apply {
            kaKaoData = kaKaoDataList[position]
            lastIndex = kaKaoDataList[position].textList[position].last()
            noticeScore = kaKaoDataList[position].textList[position].size.toString()
            setVisible = visibleSettingList[position]
        }


        holder.itemView.setOnClickListener {
            clickEvent(holder.adapterPosition)
        }


    }
}