package com.example.agentapp.activities

import android.animation.ValueAnimator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agentapp.R
import com.example.agentapp.adapters.SectionPagerAdaptor
import com.example.agentapp.fragments.MonthFragment
import com.example.agentapp.fragments.WeekFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.yearMonth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_week.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

class DashboardActivity : AppCompatActivity() {

//    private val tabTitle = arrayListOf("Week","Month")

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

//        val weekFragment = WeekFragment()
//        val monthFragment = MonthFragment()
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment,weekFragment)
//            commit()
//        }
//        radio_week.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.flFragment,weekFragment)
//                commit()
//            }
//        }
//        radio_month.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.flFragment,monthFragment)
//                commit()
//            }
//        }
    }
}
