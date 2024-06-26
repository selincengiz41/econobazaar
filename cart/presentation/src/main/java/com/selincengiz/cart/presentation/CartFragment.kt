package com.selincengiz.cart.presentation

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.cart.domain.model.ProductXUI
import com.selincengiz.cart.presentation.databinding.FragmentCartBinding
import com.selincengiz.core.common.RemoteConfigManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CartFragment : Fragment() {
    private val viewModel by viewModels<CartViewModel>()
    private val adapter by lazy { ProductAdapter(::onItemClicked) }
    private val args by navArgs<CartFragmentArgs>()
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        observe()
        viewModel.getCarts(args.Id)
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    CartState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.progressLayout.visibility = View.GONE

                    }

                    is CartState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is CartState.Data -> {
                        binding.progressBar.visibility = View.GONE
                        binding.progressLayout.visibility = View.VISIBLE
                        adapter.submitList(state.cart.products)
                        binding.tvTotal.text = "$${state.cart.total}"
                    }

                    else -> {}
                }
            }
        }
    }

    private fun onItemClicked(product: ProductXUI) {
        val bundle = bundleOf("productId" to product.id)
        findNavController().navigate(
            com.selincengiz.detail.presentation.R.id.detail_nav_graph,
            bundle
        )
    }
}