package com.selincengiz.search.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.search.domain.model.ProductUI
import com.selincengiz.search.presentation.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { ProductAdapter(::onItemClicked) }
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        binding.searchView.setOnQueryTextListener(this)
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {
                    SearchState.Entry -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    SearchState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is SearchState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is SearchState.Data -> {
                        state.list.collectLatest { list ->
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.placeholder.visibility = View.GONE
                            adapter.submitData(list)
                        }
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        text?.let {
            viewModel.searchProducts(it)
        }
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        text?.let {
            if (it.length > 3) {
                viewModel.searchProducts(it)
            }
        }
        return true
    }

    private fun onItemClicked(product: ProductUI) {
        val bundle = bundleOf("productId" to product.id)
        findNavController().navigate(
            com.selincengiz.detail.presentation.R.id.detail_nav_graph,
            bundle
        )
    }
}