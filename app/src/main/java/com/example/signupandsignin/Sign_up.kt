package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Sign_up : AppCompatActivity() {
    lateinit var signUpBU :Button
    lateinit var firstNameEt :EditText
    lateinit var lastNameEt :EditText
    lateinit var emailEt :EditText
    lateinit var passwordEt :EditText
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpBU = findViewById(R.id.signUpBU)
        firstNameEt = findViewById(R.id.firstNameEt)
        lastNameEt = findViewById(R.id.lastNameEt)
        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)

        signUpBU.setOnClickListener{
            val firstName = firstNameEt.text.toString()
            val lastName = lastNameEt.text.toString()
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()
           var k= databaseHelper.saveData(firstName, lastName,email,password)
            Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()


            if (k.equals("-1")) {
                Toast.makeText(applicationContext, "Error data not saved!", Toast.LENGTH_SHORT)
                    .show();
            } else {
                val intent = Intent(this, profile::class.java)
                intent.putExtra("firstName", firstName)
                intent.putExtra("lastName", lastName)
                intent.putExtra("email", email)
                intent.putExtra("password", password)

                startActivity(intent)
            }



        }
    }



}