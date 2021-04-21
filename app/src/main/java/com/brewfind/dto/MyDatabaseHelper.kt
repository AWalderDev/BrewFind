package com.brewfind.dto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * the Database Helper for create/init/upgrade database
 */
class MyDatabaseHelper(var context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    public var createBook = "create table if not exists Brewery(" +
            "id varchar(50)," +
            "name varchar(50)," +
            "size varchar(50)," +
            "street varchar(50)," +
            "city varchar(50)," +
            "state varchar(50)," +
            "postalCode varchar(50)," +
            "country varchar(50)," +
            "longitude varchar(50)," +
            "latitude varchar(50)," +
            "phone varchar(50)," +
            "website varchar(50)," +
            "distance varchar(50)" +
            ")"

    /**
     *
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        Toast.makeText(context, "Create Successed", Toast.LENGTH_LONG).show()
    }

    /**
     *
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Brewery")
        onCreate(db)
    }
}