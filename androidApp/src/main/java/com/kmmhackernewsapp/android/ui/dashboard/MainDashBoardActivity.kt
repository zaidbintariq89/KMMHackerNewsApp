package com.kmmhackernewsapp.android.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kmmhackernewsapp.android.R
import com.kmmhackernewsapp.android.databinding.ActivityDashboardBinding
import com.kmmhackernewsapp.android.ui.billing.BillingFragment
import com.kmmhackernewsapp.android.ui.messages.MessagesFragment
import com.kmmhackernewsapp.android.ui.more.MoreFragment
import com.kmmhackernewsapp.android.ui.services.ServicesFragment
import com.kmmhackernewsapp.android.ui.support.SupportFragment

class MainDashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.services_tab -> replaceFragment(ServicesFragment())
                R.id.billing_tab -> replaceFragment(BillingFragment())
                R.id.messages_tab -> replaceFragment(MessagesFragment())
                R.id.support_tab -> replaceFragment(SupportFragment())
                R.id.more_tab -> replaceFragment(MoreFragment())
            }
            true
        }

        // Set the default fragment when the activity starts
        binding.bottomNavigation.selectedItemId = R.id.services_tab
    }

    @SuppressLint("CommitTransaction")
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.container.id, fragment)
            commit()
        }
    }
}