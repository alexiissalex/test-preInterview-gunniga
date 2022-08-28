package com.example.agentapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.agentapp.R
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.Size
import kotlinx.android.synthetic.main.calendar_day_legend.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_week.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class WeekFragment : Fragment() {

    private var selectedDate = LocalDate.now()

    private val dateFormatter = DateTimeFormatter.ofPattern("dd")
    private val dayFormatter = DateTimeFormatter.ofPattern("EEE")
    private val monthFormatter = DateTimeFormatter.ofPattern("MMM")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dm = DisplayMetrics()
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        exSevenCalendar.apply {
            val dayWidth = dm.widthPixels / 5
            val dayHeight = (dayWidth * 1.25).toInt()
            daySize = Size(dayWidth, dayHeight)
        }

        class DayViewContainer(view: View) : ViewContainer(view) {
//            val bind = WeekFragmentCalendarDayBinding.bind(view)
            lateinit var day: CalendarDay
            init {
                view.setOnClickListener {
                    val firstDay = exSevenCalendar.findFirstVisibleDay()
                    val lastDay = exSevenCalendar.findLastVisibleDay()
                    if (firstDay == day) {
                        // If the first date on screen was clicked, we scroll to the date to ensure
                        // it is fully visible if it was partially off the screen when clicked.
                        exSevenCalendar.smoothScrollToDate(day.date)
                    } else if (lastDay == day) {
                        // If the last date was clicked, we scroll to 4 days ago, this forces the
                        // clicked date to be fully visible if it was partially off the screen.
                        // We scroll to 4 days ago because we show max of five days on the screen
                        // so scrolling to 4 days ago brings the clicked date into full visibility
                        // at the end of the calendar view.
                        exSevenCalendar.smoothScrollToDate(day.date.minusDays(4))
                    }

                    // Example: If you want the clicked date to always be centered on the screen,
                    // you would use: exSevenCalendar.smoothScrollToDate(day.date.minusDays(2))

                    if (selectedDate != day.date) {
                        val oldDate = selectedDate
                        selectedDate = day.date
                        exSevenCalendar.notifyDateChanged(day.date)
                        oldDate?.let { exSevenCalendar.notifyDateChanged(it) }
                    }
                }
            }

            fun bind(day: CalendarDay) {
                this.day = day
                exSevenDateText.text = dateFormatter.format(day.date)
                exSevenDayText.text = dayFormatter.format(day.date)
                exSevenMonthText.text = monthFormatter.format(day.date)

//                exSevenDateText.setTextColor(view.context.getColor(if (day.date == selectedDate) R.color.example_7_blue else R.color.example_7_white))
//                exSevenSelectedView.isVisible = day.date == selectedDate

            }
        }

        exSevenCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) = container.bind(day)

        }

        val currentMonth = YearMonth.now()
        // Value for firstDayOfWeek does not matter since inDates and outDates are not generated.
        exSevenCalendar.setup(currentMonth, currentMonth.plusMonths(3), DayOfWeek.values().random())
        exSevenCalendar.scrollToDate(LocalDate.now())

    }

}