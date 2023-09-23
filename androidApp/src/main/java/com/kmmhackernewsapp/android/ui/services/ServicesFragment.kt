package com.kmmhackernewsapp.android.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    }
}