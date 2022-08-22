package com.example.agentapp.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agentapp.R
import com.example.agentapp.adapters.SectionPagerAdaptor
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val tabTitle = arrayListOf("Week","Month")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.title = "Dashboard"

        val navView:BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_dashboard,R.id.navigation_Lists_of_rent,R.id.navigation_To_do_list))
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

        setUpTabLayoutWithViewPager()
    }

    //fun setup Tab layout in fragment (week/month)
    private fun setUpTabLayoutWithViewPager() {
        viewPager.adapter = SectionPagerAdaptor(this)
        TabLayoutMediator(tabLayout,viewPager){ tab , position ->
            tab.text = tabTitle[position]
        }.attach()
    //setup custom tap layout of fragment (week/month)
        for(i in 0..2){
            val textView = LayoutInflater.from(this).inflate(R.layout.tab_title,null)
            as TextView
            tabLayout.getTabAt(i)?.customView = textView
        }

    }
}