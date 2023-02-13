package com.online.e_comcanada

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Login : AppCompatActivity() {

    lateinit var edit_email:EditText
    lateinit var edit_password:EditText
    lateinit var btn_loginscreen: ImageButton
    lateinit var linkNewReq: TextView
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DB = DB_Functions(this)

        edit_email = findViewById(R.id.editLoginEmailAddress)
        edit_password = findViewById(R.id.editLoginTextPassword)
        btn_loginscreen = findViewById(R.id.loginImage)
        linkNewReq = findViewById(R.id.registerlink)
        btn_loginscreen.setOnClickListener {

            val logins =  Login()
            val username: String = edit_email.getText().toString()
            val password: String = edit_password.getText().toString()
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@Login, "All fields required!", Toast.LENGTH_SHORT)
                    .show()
            } else if (logins.isEmailValid(username) == false) {
                Toast.makeText(this@Login, "Invalid Email", Toast.LENGTH_SHORT).show()
            } else {
                val checkuserpass = DB!!.checkusernamepass(username, password)
                if (checkuserpass == true) {
                    val i = Intent(this@Login, UserInfo::class.java)
                    i.putExtra("username", username)
                    Toast.makeText(this@Login, "Login Successful!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(i)
                } else {
                    Toast.makeText(this@Login, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }



        linkNewReq.setOnClickListener {
            startActivity(Intent(this@Login, Signup::class.java))
        }

    }
}