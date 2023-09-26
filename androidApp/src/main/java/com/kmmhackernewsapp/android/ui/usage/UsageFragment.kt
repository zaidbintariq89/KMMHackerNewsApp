package com.kmmhackernewsapp.android.ui.usage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kmmhackernewsapp.android.databinding.UsageFragmentBinding

class UsageFragment : Fragment() {

    private lateinit var binding: UsageFragmentBinding
    private var viewModel: UsageViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UsageViewModel::class.java]
        viewModel?.getAllPromotions()

        viewModel?.promotionLiveData?.observe(viewLifecycleOwner) {
            it?.let { response ->
                val promotion = response.content.promotions.first()
                binding.promotionLV.promotionTitleTxt.text = promotion.title
                binding.promotionLV.promotionDescriptionTxt.text = promotion.description
                Glide.with(this).load(promotion.image).into(binding.promotionLV.ivImages)
            }
        }
    }
}