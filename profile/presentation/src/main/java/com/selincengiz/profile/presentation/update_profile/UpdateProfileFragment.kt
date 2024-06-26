package com.selincengiz.profile.presentation.update_profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.profile.presentation.R
import com.selincengiz.profile.presentation.databinding.FragmentUpdateProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateProfileFragment : Fragment(), AdapterView.OnItemClickListener {
    private lateinit var binding: FragmentUpdateProfileBinding
    private val args by navArgs<UpdateProfileFragmentArgs>()
    private val viewModel by viewModels<UpdateProfileViewModel>()
    private lateinit var spinnerList: List<String>
    private var selectedSpinner: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false)
        binding.updateProfileFragment = this
        binding.act.onItemClickListener = this@UpdateProfileFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setAdapterToSpinner()
        with(binding) {
            val backgroundColor = RemoteConfigManager.getBackgroundColor()
            mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
            tvAge.setText(args.user.age.toString())
            tvFirstName.setText(args.user.firstName)
            tvLastName.setText(args.user.lastName)
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {

                    UpdateProfileState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is UpdateProfileState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.throwable, Snackbar.LENGTH_SHORT).show()
                    }

                    is UpdateProfileState.Data -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, "Successful update!", Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setAdapterToSpinner() {
        spinnerList = listOf(" ", "0+", "0-", "A+", "A-", "B+", "B-", "AB+", "AB-")

        val saveTypeAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerList
        )
        binding.act.setAdapter(saveTypeAdapter)
        binding.act.setText(args.user.bloodGroup, false)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        selectedSpinner = if (spinnerList[position] == " ") {
            null
        } else {
            spinnerList[position]
        }
    }

    fun onClick() {
        findNavController().navigateUp()
    }

    fun onSave() = with(binding) {
        val age = tvAge.text.toString()
        val firstName = tvFirstName.text.toString()
        val lastName = tvLastName.text.toString()
        val user = args.user.copy(
            age = age.toInt(),
            firstName = firstName,
            lastName = lastName,
            bloodGroup = selectedSpinner ?: ""
        )
        viewModel.updateProfile(user.id, user)
    }
}