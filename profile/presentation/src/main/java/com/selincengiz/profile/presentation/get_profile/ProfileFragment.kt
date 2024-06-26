package com.selincengiz.profile.presentation.get_profile

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
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.selincengiz.core.common.Extensions.loadUrlCircle
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.presentation.R
import com.selincengiz.profile.presentation.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()
    private val args by navArgs<ProfileFragmentArgs>()
    private lateinit var userUI: UserUI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.profileFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = RemoteConfigManager.getBackgroundColor()
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
        viewModel.getProfile(args.Id)
        observe()
    }

    fun onClick() {
        findNavController().navigate(ProfileFragmentDirections.profileToUpdate(userUI))
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    ProfileState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.progressLayout.visibility = View.GONE
                    }

                    is ProfileState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is ProfileState.Data -> with(binding) {
                            progressBar.visibility = View.GONE
                            progressLayout.visibility = View.VISIBLE
                            userUI = state.user
                            tvName.text = state.user.firstName + " " + state.user.lastName
                            tvEmail.text = state.user.email
                            ivProfile.loadUrlCircle(state.user.image)
                            viewPager.adapter = ViewPagerAdapter(this@ProfileFragment, state.user)

                            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                                when (position) {
                                    0 -> tab.text =
                                        getString(com.selincengiz.core.common.R.string.personal_info)

                                    1 -> tab.text =
                                        getString(com.selincengiz.core.common.R.string.company)

                                    2 -> tab.text =
                                        getString(com.selincengiz.core.common.R.string.address1)
                                }
                            }.attach()
                        }

                    else -> {}
                }
            }
        }
    }
}