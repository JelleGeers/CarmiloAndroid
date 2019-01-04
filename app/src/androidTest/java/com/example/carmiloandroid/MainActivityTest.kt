package com.example.carmiloandroid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.example.carmiloandroid.Activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    val activityRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun mainScreen_loads(){
        onView(withId(R.id.btnStart)).perform(click())
    }

    @Test
    fun addRide(){
        onView(withId(R.id.btnStart)).perform(click())
        onView(withId(R.id.nav_addRide)).perform(click())
        onView(withId(R.id.txtDeparture)).perform(ViewActions.typeText("Lochristi")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.txtDate)).perform(ViewActions.typeText("2019-04-08")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.txtStreet)).perform(ViewActions.typeText("Burglaan")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.txtHouseNr)).perform(ViewActions.typeText("5")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.txtZipcode)).perform(ViewActions.typeText("9080")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.txtMaxPassengers)).perform(ViewActions.typeText("4")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btnAddRide)).perform(click())
        onView(withId(R.id.nav_home)).perform(click())
    }

    @Test
    fun viewAllRides(){
        onView(withId(R.id.btnStart)).perform(click())
        onView(withId(R.id.nav_allRides)).perform(click())
    }

    @Test
    fun viewAllRidesUser(){
        onView(withId(R.id.btnStart)).perform(click())
        onView(withId(R.id.nav_home)).perform(click())
    }

}