package com.example.githubuserapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.databinding.ItemViewFavoriteBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val list = mutableListOf<DetailUsersResponse>()

    inner class FavoriteViewHolder(
        private val binding: ItemViewFavoriteBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailUsersResponse) {
            binding.data = data
        }
    }

    fun setData(list: List<DetailUsersResponse>?) {
        this.list.clear()
        if (!list.isNullOrEmpty()) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
       return FavoriteViewHolder(
           DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.item_view_favorite, parent, false)
       )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}