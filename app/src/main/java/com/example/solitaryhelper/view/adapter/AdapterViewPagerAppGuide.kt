package com.example.solitaryhelper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityAppGuideBinding
import com.example.solitaryhelper.databinding.ViewholderViewpagerAppguideitemBinding
import com.example.solitaryhelper.view.contents.Contents

class AdapterViewPagerAppGuide(private val imageList:List<String> = listOf()) :
    RecyclerView.Adapter<AdapterViewPagerAppGuide.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ViewholderViewpagerAppguideitemBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_viewpager_appguideitem, parent, false)
        )

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            imageUri = imageList[holder.adapterPosition]
        }
    }

}