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
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentCalendarBinding
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
            Toast.makeText(this.requireContext(), date, Toast.LENGTH_SHORT).show() //TODO: Remove that in the end
            fillRecyclerView(date)
        }
    }

    private fun fillRecyclerView(date: String){
        //frogTaskViewModel.dateInitialize(date)
        //TODO: Have the List fill the recyclerview with adapter and above command
        val recyclerview = view?.findViewById<RecyclerView>(R.id.calendarRecyclerView)
        val calendarAdapter =
    }

    private fun createDateString(year: Int, month: Int, dayOfMonth: Int): String{
        //Assigning Values is not very nice but it makes the code more understandable overall
        var dayOfMonthString = "12"
        var monthString = "12"
        var yearString = "2000"

        if(dayOfMonth < 10){
            dayOfMonthString = "0$dayOfMonth"
        } else {
            dayOfMonthString = "$dayOfMonth"
        }
        if(month < 10){
            monthString = "0$month"
        } else {
            monthString = "$month"
        }
        yearString = "$year"

        return "$dayOfMonthString.$monthString.$yearString"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}