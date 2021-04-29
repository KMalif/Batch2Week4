package com.mobile.batch2week4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.batch2week4.databinding.ListItemBinding
import com.mobile.batch2week4.model.Posts

class MainAdapter(private var posts : List<Posts>, val listener : MainAdapter.onAdapterCLick):RecyclerView.Adapter<MainAdapter.PostsViewholder>() {

    inner class PostsViewholder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewholder {
        return PostsViewholder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsViewholder, position: Int) {
        holder.binding.apply {
            TvTitle.text = posts[position].title
            TvTitle.setOnClickListener {
                listener.onCLick(posts[position])
            }
            IconUpdate.setOnClickListener {
                listener.onUpdate(posts[position])
            }
        }
    }

    interface onAdapterCLick{
        fun onCLick(posts: Posts)
        fun onUpdate(posts: Posts)
    }
}