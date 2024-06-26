package com.selincengiz.detail.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductCartUI
import com.selincengiz.detail.domain.model.ProductUI
import com.selincengiz.detail.presentation.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val adapterTag by lazy { TagAdapter() }
    private var favoriteState: Boolean = false
    lateinit var product: ProductUI
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.detailFunctions = this
        binding.tagRecycler.adapter = adapterTag
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        viewModel.getDetail(args.productId)
        viewModel.isFavorite(args.productId)
        observe()
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    DetailState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is DetailState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is DetailState.IsFavorite -> {
                        if (state.isFavorite) {
                            binding.btnLike.setBackgroundResource(com.selincengiz.core.common.R.drawable.ic_heart_filled)
                            favoriteState = true
                        } else {
                            binding.btnLike.setBackgroundResource(com.selincengiz.core.common.R.drawable.ic_heart)
                            favoriteState = false

                        }
                    }

                    is DetailState.Data -> with(binding) {
                        progressBar.visibility = View.GONE
                        product = state.product
                        category.text = product.category
                        viewPager.adapter = SliderAdapter(product.images)
                        viewPager()
                        tvBrandProduct.text = product.brand
                        tvDescProduct.text = product.description
                        tvTitleProduct.text = product.title
                        tvPrice.text = "$${product.price}"
                        adapterTag.submitList(product.tags)
                    }

                    is DetailState.AddCart -> {
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun viewPager() = with(binding) {
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(40))
        pageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPager.setPageTransformer(pageTransformer)
    }


    fun backClicked() {
        findNavController().navigateUp()
    }

    fun likeClicked() {
        if (favoriteState) {
            viewModel.deleteFavorite(product)
            binding.btnLike.setBackgroundResource(com.selincengiz.core.common.R.drawable.ic_heart)
            favoriteState = false
        } else {
            viewModel.addFavorite(product)
            binding.btnLike.setBackgroundResource(com.selincengiz.core.common.R.drawable.ic_heart_filled)
            favoriteState = true
        }
    }

    fun onAddCart() {
        val cart = CartUI(listOf(ProductCartUI(product.id, 1)), 2)
        viewModel.addCart(cart)
    }
}