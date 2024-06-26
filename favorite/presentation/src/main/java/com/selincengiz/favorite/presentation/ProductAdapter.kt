package com.selincengiz.favorite.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.core.common.Extensions.loadUrl
import com.selincengiz.favorite.domain.model.ProductUI
import com.selincengiz.favorite.presentation.databinding.ItemFavoriteBinding

class ProductAdapter (
    private val onItemClicked: (ProductUI) -> Unit
) : ListAdapter<ProductUI, ProductAdapter.ProductViewHolder>(
    ProductDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ProductViewHolder(
        private val binding: ItemFavoriteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductUI) = with(binding) {
            ivProduct.loadUrl(product.images[0])
            tvPrice.text = "$${product.price}"
            tvTitleProduct.text = product.title
          /*  tvDescProduct.text = product.quantity.toString()
            tvCount.text = product.quantity.toString()*/

            root.setOnClickListener {
                onItemClicked(product)
            }
        }
    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<ProductUI>() {
        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }
    }
}