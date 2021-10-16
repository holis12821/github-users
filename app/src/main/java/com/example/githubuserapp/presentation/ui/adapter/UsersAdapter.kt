/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.ItemUserListBinding

class UsersAdapter:
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

     val list = mutableListOf<ItemsItem>()

    lateinit var listener: AdapterClickListener<ItemsItem?>

    inner class UsersViewHolder(
      private val binding: ItemUserListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsItem?) {
            binding.data = data
            itemView.setOnClickListener {
                listener.onItemClickCallback(data = data)
            }
            binding.btnAddFavorite.setOnClickListener {
                listener.onViewClickCallback(it, data)
            }
        }
    }


    fun setData(list: List<ItemsItem>?) {
        this.list.clear()
        if (!list.isNullOrEmpty()) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}