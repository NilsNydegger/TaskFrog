package com.example.taskfrog.ui.calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentCalendarBinding
import com.example.taskfrog.room.FrogTask
import com.example.taskfrog.room.FrogTaskViewModel

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private lateinit var frogTaskViewModel: FrogTaskViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        frogTaskViewModel = ViewModelProvider(this)[FrogTaskViewModel::class.java]

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date: String = createStringFromDate(year, month, dayOfMonth)
            fillRecyclerView(date)
        }
    }

    @SuppressLint("SetTextI18n") //There is currently no need for translations, one language is fine for now
    private fun fillRecyclerView(date: String) {
        //TODO: This should optimally be a recyclerview but not for now
        frogTaskViewModel.dateInitialize(date)
        val textTaskListView = view?.findViewById<TextView>(R.id.calendarTasksOfDay)
        //The separate thread takes too long so we need to have a fake loading time
        Thread.sleep(20)
        frogTaskViewModel.getAllTasksFromDate
        val listOfTasksOfDay: List<FrogTask> = frogTaskViewModel.getAllTasksFromDate
        if (listOfTasksOfDay.isNotEmpty()) {
            var textListOfLists = "Tasks for Today:"
            var lineOfTask: String
            for (frogList in listOfTasksOfDay) {
                lineOfTask = "${frogList.task_name} - ${frogList.task_description}"
                textListOfLists = "$textListOfLists \n \n $lineOfTask"
            }
            textTaskListView?.text = textListOfLists
        } else {
            textTaskListView?.text = "There are no Tasks for Today"
        }
    }

    fun createStringFromDate(year: Int, month: Int, dayOfMonth: Int): String {
        //Assigning Values is not very nice but it makes the code more understandable overall
        var dayOfMonthString = "12"
        var monthString = "12"
        var yearString = "2000"
        val correctMonth = month + 1

        dayOfMonthString = if (dayOfMonth < 10) {
            "0$dayOfMonth"
        } else {
            "$dayOfMonth"
        }
        monthString = if (correctMonth < 10) {
            "0$correctMonth"
        } else {
            "$correctMonth"
        }
        yearString = "$year"

        return "$dayOfMonthString.$monthString.$yearString"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}