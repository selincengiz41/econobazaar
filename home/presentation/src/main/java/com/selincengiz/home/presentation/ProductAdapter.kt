package com.selincengiz.home.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.core.common.Extensions.loadUrl
import com.selincengiz.home.domain.model.ProductUI
import com.selincengiz.home.presentation.databinding.ItemProductBinding


class ProductAdapter(
    private val onItemClicked: (ProductUI) -> Unit
) : PagingDataAdapter<ProductUI, ProductAdapter.ProductViewHolder>(
    ProductDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemProductBinding.inflate(
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
        private val binding: ItemProductBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductUI) = with(binding) {
            ivProduct.loadUrl(product.images[0])
            tvPrice.text = "$${product.price}"
            tvTitleProduct.text = product.title
            tvDescProduct.text = product.brand

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