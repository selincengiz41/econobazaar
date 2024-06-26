package com.selincengiz.categories.presentation.categories_product

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.categories.domain.model.ProductUI
import com.selincengiz.categories.presentation.databinding.FragmentCategoriesProductBinding
import com.selincengiz.core.common.RemoteConfigManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesProductFragment : Fragment() {
    private val viewModel by viewModels<CategoriesProductViewModel>()
    private val adapter by lazy { ProductAdapter(::onItemClicked) }
    private val args by navArgs<CategoriesProductFragmentArgs>()
    private lateinit var binding: FragmentCategoriesProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesProductBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.categoryProductFunctions = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        binding.category.text = args.category.name
        viewModel.getCategoriesProduct(args.category.name)
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    CategoryProductState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is CategoryProductState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is CategoryProductState.Data -> {
                        state.list.collectLatest { list ->
                            binding.progressBar.visibility = View.GONE
                            adapter.submitData(list)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun backClicked() {
        findNavController().navigateUp()
    }

    fun searchClicked() {
        findNavController().navigate(com.selincengiz.search.presentation.R.id.search_nav_graph)
    }

    private fun onItemClicked(product: ProductUI) {
        val bundle = bundleOf("productId" to product.id)
        findNavController().navigate(
            com.selincengiz.detail.presentation.R.id.detail_nav_graph,
            bundle
        )
    }
}