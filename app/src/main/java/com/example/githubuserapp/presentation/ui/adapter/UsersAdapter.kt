/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.ItemViewUserListBinding
import com.example.githubuserapp.presentation.ui.adapter.callback.AdapterClickListener

class UsersAdapter(private val fragment: Fragment = Fragment()) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    val list = mutableListOf<ItemsItem>()

    var listener: AdapterClickListener<ItemsItem>? = null

    inner class UsersViewHolder(
        private val binding: ItemViewUserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsItem) {
            binding.data = data
            itemView.setOnClickListener {
                listener?.onItemClickCallback(data = data, fragment)
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
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view_user_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}