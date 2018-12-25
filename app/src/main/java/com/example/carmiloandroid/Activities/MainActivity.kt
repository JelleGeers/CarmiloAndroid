package com.example.carmiloandroid.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.carmiloandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val credentialsView = findViewById(R.id.credentials) as TextView
        val logoutButton = findViewById(R.id.logout) as Button
        logoutButton.setOnClickListener { logout() }
        btnStart.setOnClickListener{start() }

        //Obtain the token from the Intent's extras
        val accessToken = intent.getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN)
        credentialsView.text = accessToken
    }


    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(LoginActivity.KEY_CLEAR_CREDENTIALS, true)
        startActivity(intent)
        finish()
    }

    private fun start() {
        startActivity(Intent(applicationContext, MainActivityNavigation::class.java))
    }
}