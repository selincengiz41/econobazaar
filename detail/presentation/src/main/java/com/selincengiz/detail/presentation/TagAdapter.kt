package com.selincengiz.detail.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.detail.presentation.databinding.ItemTagBinding

class TagAdapter : ListAdapter<String, TagAdapter.TagViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder =
        TagViewHolder(
            ItemTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class TagViewHolder(
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(tag: String) = with(binding) {
            tvTag.text = "#$tag"
        }
    }

    class CategoryDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

