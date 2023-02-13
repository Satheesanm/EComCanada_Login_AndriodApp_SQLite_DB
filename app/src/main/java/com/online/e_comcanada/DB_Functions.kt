package com.online.e_comcanada

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB_Functions(context: Context?) :
    SQLiteOpenHelper(context, "user_info.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table users(username TEXT, password TEXT, fname TEXT, lname TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("drop table if exists users")
    }

    fun insertData(
        username: String?,
        password: String?,
        fname: String?,
        lname: String?,

    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        values.put("fname", fname)
        values.put("lname", lname)
        val result = db.insert("users", null, values)
        return if (result == -1L) false else true
    }

    fun updateData(username: String, fname: String?, lname: String?): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("fname", fname)
        values.put("lname", lname)
        val result = db.update("users", values, "username=?", arrayOf(username)).toLong()
        return if (result == -1L) false else true
    }

    fun checkusernamepass(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery(
            "select * from users where username=? and password=?",
            arrayOf(username, password)
        )
        return if (cursor.count > 0) true else false
    }

    fun searchData(username: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from users where username=?", arrayOf(username))
    }

    companion object {
        const val DBNAME = "user_info.db"
    }
}