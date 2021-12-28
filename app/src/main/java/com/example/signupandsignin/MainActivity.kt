package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var signInBu:Button
    lateinit var signUPBu:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInBu = findViewById(R.id.signInBu)
        signUPBu = findViewById(R.id.signUPBu)

        signInBu.setOnClickListener{
            val intent = Intent(this, Sign_In::class.java)
            startActivity(intent)
        }
        signUPBu.setOnClickListener{
            val intent = Intent(this, Sign_up::class.java)
            startActivity(intent)
        }
    }
}