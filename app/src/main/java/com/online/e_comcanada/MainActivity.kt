package com.online.e_comcanada

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

lateinit var splashLoginBtn: Button;
lateinit var splashSignUpBtn: Button;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashLoginBtn= findViewById(R.id.login);
        splashSignUpBtn=findViewById(R.id.signup);
        splashLoginBtn.setOnClickListener { startActivity(Intent(this@MainActivity, Login::class.java)) }
        splashSignUpBtn.setOnClickListener { startActivity(Intent(this@MainActivity, Signup::class.java)) }
    }



}