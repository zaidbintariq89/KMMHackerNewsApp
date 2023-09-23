package com.kmmhackernewsapp.android.ui.services

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kmmhackernewsapp.android.ui.equipment.EquipmentFragment
import com.kmmhackernewsapp.android.ui.packgetab.PackageFragment
import com.kmmhackernewsapp.android.ui.usage.UsageFragment

class ServicesPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UsageFragment()
            1 -> EquipmentFragment()
            2 -> PackageFragment()
            else -> UsageFragment()
        }
    }
}