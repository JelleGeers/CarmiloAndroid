package com.example.carmiloandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val credentialsView = findViewById(R.id.credentials) as TextView
        val logoutButton = findViewById(R.id.logout) as Button
        logoutButton.setOnClickListener { logout() }

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
}