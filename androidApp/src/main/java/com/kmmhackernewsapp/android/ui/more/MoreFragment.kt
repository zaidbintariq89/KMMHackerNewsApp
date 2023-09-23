package com.kmmhackernewsapp.android.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kmmhackernewsapp.android.databinding.MoreFragmentBinding

class MoreFragment : Fragment() {

    private lateinit var binding: MoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}