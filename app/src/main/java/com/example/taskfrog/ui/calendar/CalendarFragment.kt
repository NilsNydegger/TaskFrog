package com.example.taskfrog.ui.calendar

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentCalendarBinding
import com.example.taskfrog.room.FrogTask
import com.example.taskfrog.room.FrogTaskViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

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
            //Toast.makeText(this.requireContext(), date, Toast.LENGTH_SHORT).show() //TODO: Remove that in the end
            fillRecyclerView(date)
        }
    }

    private fun fillRecyclerView(date: String){
        //TODO: Critical Code:
        frogTaskViewModel.dateInitialize(date)
        frogTaskViewModel.getAllTasksFromDate
        //Critical Code:
        var calendarFrogListList: List<FrogTask> = frogTaskViewModel.getAllTasksFromDate
        if(calendarFrogListList.isNotEmpty()) {
            var frogTaskTask: FrogTask = calendarFrogListList[0]
            Toast.makeText(this.requireContext(), frogTaskTask.task_name, Toast.LENGTH_SHORT).show()
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