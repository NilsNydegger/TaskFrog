package com.example.taskfrog

import com.example.taskfrog.ui.calendar.CalendarFragment
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

class UnitTests {

    //Testing the Help-Method in Calendar Fragment
    @Test
    fun dateToStringConversion_Test(){
        val calendarFragment = CalendarFragment()
        var dateString = calendarFragment.createStringFromDate(2000, 3, 12)
        assertEquals("12.04.2000", dateString)
        dateString = calendarFragment.createStringFromDate(1000, 10, 1)
        assertEquals("01.11.1000", dateString)
    }

}