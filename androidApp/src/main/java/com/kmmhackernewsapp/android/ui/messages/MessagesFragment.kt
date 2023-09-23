package com.kmmhackernewsapp.android.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kmmhackernewsapp.android.databinding.MessagesFragmentBinding

class MessagesFragment : Fragment() {

    private lateinit var binding: MessagesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MessagesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}