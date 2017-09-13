package com.example.sasalog.orderstore.myData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sasalog on 9/13/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //create database orderstore
    private  static final String DATABASE_NAME= "orderstore.db";

    // database version 1; should be incremented each time a new schema is created
    private static final int DATABASE_VERSION= 1;

    //constructor that will take a context and call the parent constructor
    public DatabaseHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables
    //customer table sql
    private static String SQL_CREATE_CUSTOMER_TABLE="CREATE TABLE " + OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER + " (" +
            OrderStoreContract.OrderStoreEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME + "TEXT, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_LAST_NAME + "TEXT , " +
            OrderStoreContract.OrderStoreEntry.COLUMN_TELEPHONE + "TEXT " +
            "); ";
    //product table sql
    private static String SQL_CREATE_PRODUCT_TABLE="CREATE TABLE " + OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT + " (" +
            OrderStoreContract.OrderStoreEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME + "TEXT , " +
            OrderStoreContract.OrderStoreEntry.COLUMN_PRICE + "INTEGER" +
            "); ";
    //order table sql
    private static String SQL_CREATE_ORDERS_TABLE= "CREATE TABLE " + OrderStoreContract.OrderStoreEntry.TABLE_ORDERS + " (" +
            OrderStoreContract.OrderStoreEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
           // OrderStoreContract.OrderStoreEntry.COLUMN_PRICE + "INTEGER, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY + "INTEGER, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_TOTAL_AMOUNT +"INTEGER" +
            ") ";
    private static String SQL_CREATE_ORDERLIST_TABLE= "CREATE TABLE" + OrderStoreContract.OrderStoreEntry.TABLE_ORDERLIST + " (" +
            OrderStoreContract.OrderStoreEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_CUSTOMER_ID + "INTEGER, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_ID + "INTEGER, " +
            OrderStoreContract.OrderStoreEntry.COLUMN_ORDER_ID + "INTEGER" +
            ") ";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //execute sql statements
        sqLiteDatabase.execSQL(SQL_CREATE_CUSTOMER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORDERS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORDERLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //delete old tables upon data update
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderStoreContract.OrderStoreEntry.TABLE_ORDERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderStoreContract.OrderStoreEntry.TABLE_ORDERLIST);

        //create new table
        onCreate(sqLiteDatabase);
    }
}
