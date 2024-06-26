package com.selincengiz.detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.core.common.Extensions.loadUrl
import com.selincengiz.detail.presentation.databinding.ItemSliderBinding

class SliderAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = imageUrls.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bind(imageUrls[position])

    class SliderViewHolder(
        private val binding: ItemSliderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) = with(binding) {
            imageSlide.loadUrl(url)
        }
    }
}


