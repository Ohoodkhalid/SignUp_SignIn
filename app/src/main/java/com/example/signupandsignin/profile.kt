package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class profile : AppCompatActivity() {
    lateinit var welcomeTV:TextView
    lateinit var emailTV:TextView
    lateinit var passwordTV:TextView
    lateinit var signOutBu:Button
    var info= ArrayList<Registration_Information>()
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        welcomeTV = findViewById(R.id.welcomeTV )
        emailTV = findViewById(R.id.emailTV)
        passwordTV = findViewById(R.id.passwordTV)
        signOutBu = findViewById(R.id.signOutBu)

        var firstN = intent.extras?.getString("firstName")
        var lastN = intent.extras?.getString("lastName")
        var email = intent.extras?.getString("email")!!
        var password = intent.extras?.getString("password")!!

        welcomeTV.text = "Welcome $firstN $lastN"
        emailTV.text = email
        passwordTV.text = password

        signOutBu.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }


}