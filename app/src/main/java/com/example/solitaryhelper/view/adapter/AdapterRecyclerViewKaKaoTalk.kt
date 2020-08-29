package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderKakaotalkTalkBinding
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData
import com.example.solitaryhelper.view.dest.fake_kakao.FragmentFakeKakaoTalk
import com.example.solitaryhelper.view.utill.kakaoTimeDisplay



import kotlin.random.Random

class AdapterRecyclerViewKaKaoTalk(
    var kaKaoDataList: MutableList<KaKaoTalkData> = mutableListOf(),
    val clickEvent: (Int) -> Unit


) :
    RecyclerView.Adapter<AdapterRecyclerViewKaKaoTalk.ViewHolder>() {




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

    override fun getItemCount() = kaKaoDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            kaKaoData = kaKaoDataList[holder.adapterPosition]
            lastIndex = kaKaoDataList[holder.adapterPosition].itemLastText
            noticeScore = kaKaoDataList[holder.adapterPosition].chatNotification.toString()
            setVisible = kaKaoDataList[holder.adapterPosition].visibleSettingList
            time = kaKaoDataList[holder.adapterPosition].itemTimeLast?.let { kakaoTimeDisplay(it) }
        }

        holder.itemView.setOnClickListener {
            clickEvent(holder.adapterPosition)
        }
    }
}