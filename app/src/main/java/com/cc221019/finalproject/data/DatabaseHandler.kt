package com.cc221019.finalproject.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context : Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object DatabaseConfig {
        private const val dbName : String = "GameDatabase"
        private const val dbVersion : Int = 1
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.execSQL("PRAGMA foreign_keys = ON")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //TODO
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO
    }


}