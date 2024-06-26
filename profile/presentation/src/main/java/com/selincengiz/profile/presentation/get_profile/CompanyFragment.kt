package com.selincengiz.profile.presentation.get_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.presentation.R
import com.selincengiz.profile.presentation.databinding.FragmentCompanyBinding


class CompanyFragment(private val userUI: UserUI) : Fragment() {
    private lateinit var binding: FragmentCompanyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textDepartment.text = userUI.company.department
            textName.text = userUI.company.name
            textTitle.text = userUI.company.title
        }
    }
}