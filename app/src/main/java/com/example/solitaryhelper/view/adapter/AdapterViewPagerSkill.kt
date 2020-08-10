package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ViewholderViewpagerSkillBinding
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.fragment.main.tapfragments.FragmentSkill

class AdapterViewPagerSkill(val itemList: List<FragmentSkill.ViewPagerItem>) :
    RecyclerView.Adapter<AdapterViewPagerSkill.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding =
            DataBindingUtil.bind<ViewholderViewpagerSkillBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_viewpager_skill,
        parent,false))

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            val item = itemList[holder.adapterPosition]
            imageUrl = Contents.IMAGE_URL_DEFAULT_FILE_PATH + item.image
            text = item.text
        }
    }
}

