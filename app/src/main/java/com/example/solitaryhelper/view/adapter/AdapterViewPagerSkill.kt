package com.example.solitaryhelper.view.adapter

import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderViewpagerSkillBinding
import com.example.solitaryhelper.view.dest.main.tapfragments.FragmentSkill

class AdapterViewPagerSkill(var itemList: List<FragmentSkill.ViewPagerItem> = listOf(),
val imageClickEvent:()->Unit ) :
    RecyclerView.Adapter<AdapterViewPagerSkill.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ViewholderViewpagerSkillBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_viewpager_skill, parent, false)
        )

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
           imageViewInformationImage.setOnClickListener {
               imageClickEvent()
           }
           imageUrl = itemList[holder.adapterPosition].image
        }
    }


}