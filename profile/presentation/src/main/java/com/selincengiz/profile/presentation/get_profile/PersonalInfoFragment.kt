package com.selincengiz.profile.presentation.get_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.presentation.R
import com.selincengiz.profile.presentation.databinding.FragmentPersonalInfoBinding


class PersonalInfoFragment(private val userUI: UserUI) : Fragment() {
    private lateinit var binding: FragmentPersonalInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_personal_info, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ageTextView.text = userUI.age.toString()
            genderTextView.text = userUI.gender
            phoneTextView.text = userUI.phone
            birthDateTextView.text = userUI.birthDate
            bloodGroupTextView.text = userUI.bloodGroup
            heightTextView.text = userUI.height.toString()
            weightTextView.text = userUI.weight.toString()
            eyeColorTextView.text = userUI.eyeColor
            hairColorTextView.text = userUI.hair.color
            hairTypeTextView.text = userUI.hair.type
        }
    }
}