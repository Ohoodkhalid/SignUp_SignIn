package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Sign_In : AppCompatActivity() {
    lateinit var signInBU :Button
    lateinit var emailEt :EditText
    lateinit var passwordEt:EditText
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signInBU = findViewById(R.id.signInBU)
        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)


        signInBU.setOnClickListener {

            var email = emailEt.text.toString()
            var password = passwordEt.text.toString()

            var pw = databaseHelper.checkpassword(email)
            if (pw.equals(password)) {

                var kk= databaseHelper.returnFirstNLastN(email)
                var firstName = kk[0]
                var lastName = kk[1]
                Toast.makeText(applicationContext, "Sign in success!", Toast.LENGTH_SHORT).show();
               val intent = Intent(this, profile::class.java)
                intent.putExtra("firstName",firstName)
                intent.putExtra("lastName",lastName)
                intent.putExtra("email",email)
                intent.putExtra("password",password)
                startActivity(intent)



            }
            else{
                Toast.makeText(applicationContext, "Invaild details", Toast.LENGTH_SHORT).show();
            }


        }
    }


}