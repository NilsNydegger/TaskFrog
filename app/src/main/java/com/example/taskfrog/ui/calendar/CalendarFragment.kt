package com.example.taskfrog.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentCalendarBinding
import com.example.taskfrog.room.FrogTask
import com.example.taskfrog.room.FrogTaskViewModel

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private lateinit var frogTaskViewModel: FrogTaskViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        frogTaskViewModel = ViewModelProvider(this)[FrogTaskViewModel::class.java]

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val calendarView = view?.findViewById<CalendarView>(R.id.calendarView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: This
        binding.calendarView.setOnDateChangeListener{
                view, year, month, dayOfMonth ->
            val date: String = createDateString(year, month, dayOfMonth)
            fillRecyclerView(date)
            Toast.makeText(this.requireContext(), date, Toast.LENGTH_SHORT).show() //TODO: Remove that in the end
        }
    }

    private fun fillRecyclerView(date: String){
        //TODO: This should optimally be a recyclerview but not for now
        frogTaskViewModel.dateInitialize(date)
        val textTaskListView = view?.findViewById<TextView>(R.id.calendarTasksOfDay)
        //The separate thread takes too long so we need to have a fake loading time
        Thread.sleep(20)
        frogTaskViewModel.getAllTasksFromDate
        val listOfTasksOfDay: List<FrogTask> = frogTaskViewModel.getAllTasksFromDate
        if(listOfTasksOfDay.isNotEmpty()) {
            var textListOfLists = "Tasks for Today:"
            var lineOfTask: String
            for (frogList in listOfTasksOfDay){
                lineOfTask = "${frogList.task_name} - ${frogList.task_description}"
                textListOfLists = "$textListOfLists \n \n $lineOfTask"
            }
            textTaskListView?.text = textListOfLists
        } else {
            textTaskListView?.text = "There are no Tasks for Today"
        }
    }

    private fun createDateString(year: Int, month: Int, dayOfMonth: Int): String{
        //Assigning Values is not very nice but it makes the code more understandable overall
        var dayOfMonthString = "12"
        var monthString = "12"
        var yearString = "2000"
        val correctMonth = month + 1

        if(dayOfMonth < 10){
            dayOfMonthString = "0$dayOfMonth"
        } else {
            dayOfMonthString = "$dayOfMonth"
        }
        if(correctMonth < 10){
            monthString = "0$correctMonth"
        } else {
            monthString = "$correctMonth"
        }
        yearString = "$year"

        return "$dayOfMonthString.$monthString.$yearString"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}