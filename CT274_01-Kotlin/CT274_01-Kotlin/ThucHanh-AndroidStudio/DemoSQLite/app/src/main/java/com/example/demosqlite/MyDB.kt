package com.example.demosqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDB(context: Context): SQLiteOpenHelper(context, "users", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table users(id integer primary key autoincrement not null, username text, password text)")
        p0?.execSQL("insert into users(username,password) values('student1@ctu.edu.vn', '112233')")
        p0?.execSQL("insert into users(username,password) values('student2@ctu.edu.vn', '445566')")
        p0?.execSQL("insert into users(username,password) values('student3@ctu.edu.vn', '778899')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}