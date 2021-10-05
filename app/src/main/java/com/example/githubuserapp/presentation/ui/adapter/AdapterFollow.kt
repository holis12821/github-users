/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 02/10/21 11:22 PM
 * Last modified 02/10/21 11:22 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.ItemViewFollowBinding

class AdapterFollow: RecyclerView.Adapter<AdapterFollow.FollowViewHolder>() {

    private val list = mutableListOf<ItemsItem>()

   inner class FollowViewHolder(
     private val binding: ItemViewFollowBinding
   ): RecyclerView.ViewHolder(binding.root) {
       fun bind(data: ItemsItem?) {
           binding.data = data
       }
   }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ItemsItem>?) {
        this.list.clear()
        if (!list.isNullOrEmpty()) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
       return FollowViewHolder(
           DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.item_view_follow, parent, false)
       )
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }
}