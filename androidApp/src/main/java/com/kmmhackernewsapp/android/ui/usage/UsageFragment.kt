package com.kmmhackernewsapp.android.ui.usage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kmmhackernewsapp.android.databinding.BillingFragmentBinding

class UsageFragment : Fragment() {

    private lateinit var binding: BillingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BillingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}