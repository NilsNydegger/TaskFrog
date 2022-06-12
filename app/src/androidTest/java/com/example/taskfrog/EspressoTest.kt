package com.example.taskfrog

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taskfrog.ui.list.ListAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Ignore
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addList() {
        onView(withId(R.id.list_fab)).perform(click())
        onView(withId(R.id.listName)).perform(typeText("ListNameTest"), closeSoftKeyboard())
        onView(withText("Ok")).perform(click())
        Thread.sleep(3000)
    }

    @Ignore("Didn't work due to dependency issues - currently incomplete Code")
    fun chooseList(){
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(0, click())
        )

        onView(withId(R.id.mMenus)).perform(click())
        onView(withId(R.id.delete)).perform(click())
        onView(withText("Yes")).perform(click())
    }
}