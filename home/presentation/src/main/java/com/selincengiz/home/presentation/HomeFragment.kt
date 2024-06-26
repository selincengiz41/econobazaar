package com.selincengiz.home.presentation

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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.home.domain.model.ProductUI
import com.selincengiz.home.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var auth: FirebaseAuth
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { ProductAdapter(::onItemClicked) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()
        observe()
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        auth.currentUser?.let {
            binding.titleName.text = "Hello ${it.displayName!!.split(" ")[0]}"
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    HomeState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.progressLayout.visibility = View.GONE
                    }

                    is HomeState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is HomeState.Data -> {
                        state.list.collectLatest { list ->
                            binding.progressBar.visibility = View.GONE
                            binding.progressLayout.visibility = View.VISIBLE
                            adapter.submitData(list)
                        }
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