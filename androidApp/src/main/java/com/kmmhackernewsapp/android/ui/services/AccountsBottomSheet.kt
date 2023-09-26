package com.kmmhackernewsapp.android.ui.services

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kmmhackernewsapp.android.databinding.AccountsBottomSheetBinding
import com.kmmhackernewsapp.shared.entity.Account

class AccountsBottomSheet(private val accounts: List<Account>) : BottomSheetDialogFragment() {

    private lateinit var binding: AccountsBottomSheetBinding
    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAccounts()
    }

    private fun initAccounts() {
        requireContext().let {context ->
            accounts.map { it.accountNumber }.let {
                val adapter = ArrayAdapter(context, R.layout.simple_list_item_1, it)
                binding.listView.adapter = adapter
            }

            binding.listView.setOnItemClickListener { adapterView, view, position, l ->
                dismiss()
                Toast.makeText(context,accounts[position].accountNumber, Toast.LENGTH_SHORT).show()
            }
        }
    }
}