package com.selincengiz.categories.presentation.categories

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
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.categories.domain.model.CategoryUI
import com.selincengiz.categories.presentation.R
import com.selincengiz.categories.presentation.databinding.FragmentCategoriesBinding
import com.selincengiz.core.common.RemoteConfigManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val viewModel by viewModels<CategoriesViewModel>()
    private val adapter by lazy { CategoryAdapter(::onCategoryClicked) }
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        viewModel.getCategories()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    CategoryState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is CategoryState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is CategoryState.Data -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(state.list)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun onCategoryClicked(category: CategoryUI) {
        findNavController().navigate(CategoriesFragmentDirections.categoriesToProduct(category))
    }
}