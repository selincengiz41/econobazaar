package com.selincengiz.profile.presentation.get_profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.selincengiz.profile.domain.model.UserUI

class ViewPagerAdapter(fragment: Fragment, userUI: UserUI) :
    FragmentStateAdapter(fragment) {

    private val fragmentList = listOf(
        PersonalInfoFragment(userUI),
        CompanyFragment(userUI),
        AddressFragment(userUI)
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}