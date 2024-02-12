package com.zzzava.simpleshop;

import android.content.ContentValues
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(
        val context: Context,
        val factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, "app", factory, 1){
        override fun onCreate(db: SQLiteDatabase?) {
                val createUserQuery = "CREATE TABLE USER (" +
                        "id INT PRIMARY KEY," +
                        " login TEXT," +
                        " email TEXT," +
                        " password TEXT);"

                db!!.execSQL(createUserQuery)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                val deleteUserQuery = "DROP TABLE IF EXISTS USER"

                db!!.execSQL(deleteUserQuery)
                onCreate(db)
        }

        fun addUser(user: User){
                val values = ContentValues()

                values.put("login", user.login)
                values.put("email", user.email)
                values.put("password", user.password)

                val db = this.writableDatabase

                db.insert("user", null, values)

                db.close()
        }
}
