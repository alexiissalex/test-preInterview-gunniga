package com.example.agentapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.agentapp.R
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.Size
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class DashboardFragment : Fragment() {

    private var selectedDate = LocalDate.now()

//    private var today = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd")
    private val dayFormatter = DateTimeFormatter.ofPattern("EEE")
    private val selectedDates = mutableSetOf<LocalDate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dm = DisplayMetrics()
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        exSevenCalendar.apply {
            val dayWidth = dm.widthPixels / 8
            val dayHeight = (dayWidth * 1.25).toInt()
            daySize = Size(dayWidth, dayHeight)
        }

        class DayViewContainer(view: View) : ViewContainer(view) {
            val DateText = view.findViewById<TextView>(R.id.exSevenDateText)
            val DayText = view.findViewById<TextView>(R.id.exSevenDayText)
            val SelectedView = view.findViewById<View>(R.id.exSevenSelectedView)

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
                DateText.text = dateFormatter.format(day.date)
                DayText.text = dayFormatter.format(day.date)
//                exSevenMonthText.text = monthFormatter.format(day.date)

                DateText.setTextColor(view.context.getColorCompat(if (day.date == selectedDate) R.color.blue else R.color.black))
                DayText.setTextColor(view.context.getColorCompat(if (day.date == selectedDate) R.color.blue else R.color.gray))
                SelectedView.isVisible = day.date == selectedDate
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