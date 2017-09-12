package com.example.sasalog.orderstore.myData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sasalog.orderstore.CustomerContract;

/**
 * Created by sasalog on 9/13/17.
 */

public class OrderStoreDbHelper extends SQLiteOpenHelper {
    //create database orderstore
    private  static final String DATABASE_NAME= "orderstore.db";

    // database version 1; should be incremented each time a new schema is created
    private static final int DATABASE_VERSION= 1;

    //constructor that will take a context and call the parent constructor
    public OrderStoreDbHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //create table
        final String SQL_CREATE_CUSTOMER_TABLE="CREATE TABLE " + CustomerContract.CustomerEntry.TABLE_NAME + " (" +
                CustomerContract.CustomerEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                CustomerContract.CustomerEntry.COLUMN_FIRST_NAME + "TEXT NOT NULL, " +
                CustomerContract.CustomerEntry.COLUMN_LAST_NAME + "TEXT NOT NULL, " +
                CustomerContract.CustomerEntry.COLUMN_TELEPHONE + "TEXT NOT NULL" +
                "); ";
        //executing above created query
        sqLiteDatabase.execSQL(SQL_CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CustomerContract.CustomerEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
