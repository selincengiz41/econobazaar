package com.selincengiz.categories.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.categories.domain.model.CategoryUI
import com.selincengiz.categories.presentation.databinding.ItemCategoryBinding

class CategoryAdapter(private val itemClicked: (CategoryUI) -> Unit) :
    ListAdapter<CategoryUI, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryUI) = with(binding) {
            categoryBtn.text = category.name
            categoryBtn.setOnClickListener {
                itemClicked(category)
            }
        }
    }

    class CategoryDiffCallBack : DiffUtil.ItemCallback<CategoryUI>() {
        override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem == newItem
        }
    }
}

