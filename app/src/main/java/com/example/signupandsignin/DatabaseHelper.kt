package com.example.signupandsignin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context): SQLiteOpenHelper(context,"registration.db", null, 4) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table information (pk INTEGER PRIMARY KEY AUTOINCREMENT, FirstName text,LastName text,Email text,Password text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS information")
        onCreate(p0)
    }

    fun saveData(firstName: String, lastName: String, email: String, password: String) {
        val contentValues = ContentValues()
        contentValues.put("FirstName",firstName)
        contentValues.put("LastName",lastName)
        contentValues.put("Email",email)
        contentValues.put("Password",password)
        sqLiteDatabase.insert("information", null, contentValues)
    }

    fun readData(): ArrayList<Registration_Information> {
        val info = arrayListOf<Registration_Information>()

        // Read all data using cursor
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM information", null)

        if (cursor.count < 1) {  // Handle empty table
            println("No Data Found")
        } else {
            while (cursor.moveToNext()) {  // Iterate through table and populate people Array List
                val pk = cursor.getInt(0)  // The integer value refers to the column
                val FirstName = cursor.getString(1)
                val LastName = cursor.getString(2)
                val Email = cursor.getString(3)
                val Password = cursor.getString(4)
                info.add(Registration_Information(pk, FirstName, LastName, Email, Password))
            }
        }
        return info
    }
    fun checkpassword(email: String): String {
        //  sqLiteDatabase=writableDatabase

        var c: Cursor = sqLiteDatabase.query(
            "information", null, "Email=?", arrayOf(email), null, null, null
        )
        if (c.count < 1) {
            return "email not exists"
        }
        c.moveToFirst()
       var pass = c.getString(c.getColumnIndex("Password"))
        return pass
    }
    fun returnFirstNLastN(email: String): ArrayList<String>{
        //  sqLiteDatabase=writableDatabase
        val info = arrayListOf<String>()
        var c: Cursor = sqLiteDatabase.query(
            "information", null, "Email=?", arrayOf(email), null, null, null
        )
        if (c.count < 1) {
           // return "email not exists"
        }
        c.moveToFirst()
        var firstN = c.getString(c.getColumnIndex("FirstName"))
        var lastN = c.getString(c.getColumnIndex("LastName"))
        info.add(firstN)
        info.add(lastN)
        return info
    }
}