package com.selincengiz.favorite.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.favorite.domain.model.ProductUI
import com.selincengiz.favorite.presentation.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter by lazy { ProductAdapter(::onItemClicked) }
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        observe()
        viewModel.getFavorites()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    FavoriteState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is FavoriteState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is FavoriteState.Data -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(state.list)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun onItemClicked(product: ProductUI) {
        val bundle = bundleOf("productId" to product.id)
        findNavController().navigate(
            com.selincengiz.detail.presentation.R.id.detail_nav_graph,
            bundle
        )
    }
}