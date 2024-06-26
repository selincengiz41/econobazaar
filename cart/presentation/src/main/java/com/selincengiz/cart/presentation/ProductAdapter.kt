package com.selincengiz.cart.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.cart.domain.model.ProductXUI
import com.selincengiz.cart.presentation.databinding.ItemCartBinding
import com.selincengiz.core.common.Extensions.loadUrl

class ProductAdapter(
    private val onItemClicked: (ProductXUI) -> Unit
) : ListAdapter<ProductXUI, ProductAdapter.ProductViewHolder>(
    ProductDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemCartBinding.inflate(
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
        private val binding: ItemCartBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductXUI) = with(binding) {
            ivProduct.loadUrl(product.thumbnail)
            tvPrice.text = "$${product.price}"
            tvTitleProduct.text = product.title
            tvCount.text = product.quantity.toString()

            root.setOnClickListener {
                onItemClicked(product)
            }
        }
    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<ProductXUI>() {
        override fun areItemsTheSame(oldItem: ProductXUI, newItem: ProductXUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductXUI, newItem: ProductXUI): Boolean {
            return oldItem == newItem
        }
    }
}