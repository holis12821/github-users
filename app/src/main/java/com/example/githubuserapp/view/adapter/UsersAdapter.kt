package com.example.githubuserapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ItemUserListBinding
import com.example.githubuserapp.data.response.Users

class UsersAdapter(private val list: MutableList<Users>?):
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    lateinit var listener: AdapterClickListener<Users?>

    inner class UsersViewHolder(
      private val binding: ItemUserListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Users?) {
            binding.data = data
            //add circle image view in glide to load image in drawable resource id
            Glide.with(itemView.context)
                .load(data?.avatar)
                .into(binding.ciProfile)
            itemView.setOnClickListener {
                listener.onItemClickCallback(data = data)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Users>?) {
        this.list?.clear()
        if (list.isNullOrEmpty()) {
            list?.let {
                this.list?.addAll(it)
            }
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
        holder.bind(list?.get(position))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}