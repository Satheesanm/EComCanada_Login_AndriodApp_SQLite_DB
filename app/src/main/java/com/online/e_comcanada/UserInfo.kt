package com.online.e_comcanada

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserInfo : AppCompatActivity() {


    lateinit var edit_fname : TextView
    lateinit var edit_lname : TextView
    lateinit var edit_email : TextView
    lateinit var btn_logout : Button
    lateinit var DB: DB_Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        DB = DB_Functions(this)
        edit_fname = findViewById(R.id.editIuserInfoTextFirstName)
        edit_lname = findViewById(R.id.editIuserInfoTextLastName)
        edit_email = findViewById(R.id.editIuserInfoTextEmail)
        btn_logout = findViewById(R.id.logout)

        val username = intent.extras!!.getString("username")

        val c = DB.searchData(username!!)
        c.moveToNext()

        edit_fname.setText(c.getString(2).toString())
        edit_lname.setText(c.getString(3).toString())
        edit_email.setText("Email: " + c.getString(0).toString())


        btn_logout.setOnClickListener {
            Toast.makeText(this@UserInfo, "Logged out!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UserInfo, Login::class.java))
        }


    }
    }
