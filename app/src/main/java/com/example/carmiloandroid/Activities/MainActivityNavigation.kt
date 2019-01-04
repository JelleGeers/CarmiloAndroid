package com.example.carmiloandroid.Activities

import android.os.Bundle
import AllRidesUserFragment
import AllRidesFragment
import AddRideFragment
import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import com.example.carmiloandroid.Endpoint
import com.example.carmiloandroid.R
import com.example.carmiloandroid.RetrofitClientInstance
import kotlinx.android.synthetic.main.fragment_add_ride.*


class MainActivityNavigation : AppCompatActivity() {

    //Idling is nodig voor het testen zodat de server wacht tot de data is opgehaald uit de backend
    private val idlingResource= CountingIdlingResource("Navigation");

    fun getIdlingResourceInTest(): CountingIdlingResource {
        return idlingResource
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AllRidesUserFragment()
            ).commit()
        }
    }

    private val navListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var selectedFragment: Fragment? = AllRidesFragment()

            when (item.getItemId()) {
                R.id.nav_home -> selectedFragment = AllRidesUserFragment()
                R.id.nav_allRides -> selectedFragment = AllRidesFragment()
                R.id.nav_addRide -> selectedFragment = AddRideFragment()
            }
            //Wachten tot de server de data heeft opgehaald
            idlingResource.increment()
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).commit()
            idlingResource.decrement()
            return true
        }
    }
}