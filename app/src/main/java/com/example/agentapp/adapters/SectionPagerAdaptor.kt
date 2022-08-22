package com.example.agentapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.agentapp.activities.DashboardActivity
import com.example.agentapp.fragments.MonthFragment
import com.example.agentapp.fragments.WeekFragment

class SectionPagerAdaptor(fragment: DashboardActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return WeekFragment()
            1 -> return MonthFragment()
        }
        return null!!
    }
}