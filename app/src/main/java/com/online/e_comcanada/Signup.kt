package com.online.e_comcanada

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Signup : AppCompatActivity() {

    lateinit var edit_fname : EditText
    lateinit var edit_lname : EditText
    lateinit var edit_email : EditText
    lateinit var edit_pass : EditText
    lateinit var edit_pass_confo : EditText
    lateinit var btn_Signup : ImageButton
    lateinit var alreadysignInLink : TextView
    lateinit var DB: DB_Functions
    fun isEmailValid(email: String): Boolean {
        var isValid = false
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val inputStr: CharSequence = email
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val sharedPref = getSharedPreferences("myPrefe", MODE_PRIVATE)
        edit_fname=findViewById(R.id.editTextFirstName)
        edit_lname=findViewById(R.id.editTextLastName)
        edit_email=findViewById(R.id.editTextEmailAddress)
        edit_pass=findViewById(R.id.editTextPassword)
        btn_Signup=findViewById(R.id.registerBtn)
        edit_pass_confo=findViewById(R.id.editTextCofirmPassword)
        alreadysignInLink=findViewById(R.id.alreadysignInLink)

        DB = DB_Functions(this)
        val signUp =  Signup()
        btn_Signup.setOnClickListener {
            val fname: String = edit_fname.getText().toString()
            val lname: String = edit_lname.getText().toString()
            val username: String = edit_email.getText().toString()
            val password: String = edit_pass.getText().toString()
            val password_confirm: String = edit_pass_confo.getText().toString()
            if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
            ) {
                Toast.makeText(this@Signup, "All fields required!", Toast.LENGTH_SHORT)
                    .show()
            } else if (signUp.isEmailValid(username) == false) {
                Toast.makeText(this@Signup, "Invalid Email!", Toast.LENGTH_SHORT).show()
            } else if (password_confirm != password) {
                Toast.makeText(this@Signup, "Passwords do not match!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val insert = DB.insertData(username, password, fname, lname)
                if (insert == true) {
                    Toast.makeText(this@Signup, "Signup Successful!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@Signup, Login::class.java))
                } else {
                    Toast.makeText(this@Signup, "Signup Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        alreadysignInLink.setOnClickListener {
            startActivity(Intent(this@Signup, Login::class.java))
        }

    }
}