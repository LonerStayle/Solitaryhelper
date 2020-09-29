package com.example.solitaryhelper.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderTopicReusltBinding
import com.example.solitaryhelper.database.api.naver.dataholder.NaverBlog
import com.example.solitaryhelper.view.utill.toPicTextControl

/**
10대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,최신 머리추천,10대 복장  5 (뉴스 제외)
20~30대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,최신 머리추천, 최신 옷 추천 5
50대 -> 최신영화, 최신 패션 트렌드, 최신음악, 최신 뉴스,중년 남성 옷추천 4
 */
class AdapterRecyclerViewTopicResult(
    val blogList:MutableList<NaverBlog> = mutableListOf(),
    val imageList:Array<String> = arrayOf(),
    val clieckEvent:(Int)->Unit
):RecyclerView.Adapter<AdapterRecyclerViewTopicResult.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ViewholderTopicReusltBinding>(view)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.viewholder_topic_reuslt,parent,false))

    override fun getItemCount() = blogList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
                title = toPicTextControl(blogList[holder.adapterPosition].title)
                description = toPicTextControl( blogList[holder.adapterPosition].description)
                imageViewImage.setImageURI(Uri.parse(imageList[holder.adapterPosition]))
        }

        holder.itemView.setOnClickListener {
            clieckEvent(holder.adapterPosition)
        }
    }
}