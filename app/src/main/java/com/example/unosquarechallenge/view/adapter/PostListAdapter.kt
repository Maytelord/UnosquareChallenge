package com.example.unosquarechallenge.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unosquarechallenge.R
import com.example.unosquarechallenge.databinding.PostItemLayoutBinding
import com.example.unosquarechallenge.model.PostItem

class PostListAdapter : ListAdapter<PostItem, PostListAdapter.PostViewHolder>(ComicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        if (post != null) {
            holder.bind(post)
        }
    }

    class PostViewHolder(
        private val binding: PostItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var isSeeingMore = false
        fun bind(item: PostItem) {


            binding.apply {
                post = item

                seeMoreTextView.setOnClickListener {
                    isSeeingMore = !isSeeingMore

                    if(isSeeingMore){
                        postTextView.maxLines = 10
                        seeMoreTextView.text = it.context.resources.getString(R.string.see_less)
                    }else{
                        postTextView.maxLines = 2
                        seeMoreTextView.text = it.context.resources.getString(R.string.see_more)
                    }

                }
                executePendingBindings()
            }
        }


    }

}



private class ComicDiffCallback : DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem == newItem
    }
}