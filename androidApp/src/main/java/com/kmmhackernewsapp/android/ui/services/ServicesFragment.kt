package com.kmmhackernewsapp.android.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kmmhackernewsapp.android.R
import com.kmmhackernewsapp.android.databinding.ServicesFragmentBinding

class ServicesFragment : Fragment() {
    private lateinit var binding: ServicesFragmentBinding
    private var viewModel: ServicesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServicesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.services_lbl)
        viewModel = ViewModelProvider(this)[ServicesViewModel::class.java]
        initPagers()
    }

    private fun initPagers() {
        activity?.let {
            val adapter = ServicesPagerAdapter(it)
            binding.servicesPager.adapter = adapter

            // Attach the TabLayout to the ViewPager2
            TabLayoutMediator(binding.servicesTab, binding.servicesPager) { tab, position ->
                tab.text = when(position) {
                    0 -> "Usage"
                    1 -> "Equipment"
                    2 -> "Package"
                    else -> ""
                }
            }.attach()
        }
    }
}