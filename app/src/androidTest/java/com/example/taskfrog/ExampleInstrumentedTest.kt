package com.example.taskfrog

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taskfrog.ui.list.ListAdapter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.taskfrog", appContext.packageName)
    }

    @Test
    fun addList() {
        onView(withId(R.id.list_fab)).perform(click())
        onView(withId(R.id.listName)).perform(typeText("ListNameTest"), closeSoftKeyboard())
        onView(withText("Ok")).perform(click())
        Thread.sleep(3000)
    }

    @Test
    fun chooseList(){
        Espresso.onView(withId(R.id.recyclerView)).perform(
            //RecyclerViewActions.scrollToPosition<ListAdapter.ListViewHolder>(0)
        )

        onView(withId(R.id.delete)).perform(click())
        onView(withText("Yes")).perform(click())
    }
}