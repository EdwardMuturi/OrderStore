package com.example.sasalog.orderstore.myData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.example.sasalog.orderstore.Customers;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.sasalog.orderstore.myData.OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME;
import static com.example.sasalog.orderstore.myData.OrderStoreContract.OrderStoreEntry.COLUMN_LAST_NAME;
import static com.example.sasalog.orderstore.myData.OrderStoreContract.OrderStoreEntry.COLUMN_TELEPHONE;
import static com.example.sasalog.orderstore.myData.OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;

/**
 * Created by sasalog on 9/13/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
   //log tag
    private  static final String LOG= DatabaseHelper.class.getName();
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
            COLUMN_FIRST_NAME + "TEXT, " +
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
            OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_PRICE + "INTEGER, " +
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

    /*CRUD operations
      create rows
         Customer table */
    public long createCustomer(Customers customer, int orderLinesId){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(COLUMN_FIRST_NAME, customer.getFirstName());
        values.put(COLUMN_LAST_NAME, customer.getLastName());
        values.put(COLUMN_TELEPHONE, customer.getTelephone());

        //insert row
        long customerId= db.insert(TABLE_CUSTOMER, null, values);
        return customerId;
    }

    /*Read all table values
        Customer Table*/
    public List<Customers> getAllCustomers(){
        List<Customers> customers= new ArrayList<Customers>();
        String selectQuery= "SELECT * FROM " + TABLE_CUSTOMER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c= db.rawQuery(selectQuery, null);

        //loop through each row abnd add data to list
        if (c.moveToFirst() ){
            do{
                Customers cust= new Customers();
                cust.setId(c.getInt((c.getColumnIndex(_ID))));
                cust.setFirstName(c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)));
                cust.setLastName(c.getString(c.getColumnIndex(COLUMN_LAST_NAME)));
                cust.setTelephone(c.getString(c.getColumnIndex(COLUMN_TELEPHONE)));
                //Add to Cisutomers list
                customers.add(cust);
            } while (c.moveToNext() );

        }
        return customers;
    }

    //update row
    //Customer table
    public int updateCustomer(Customers customer){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values=  new ContentValues();
        values.put(COLUMN_FIRST_NAME, customer.getFirstName());
        values.put(COLUMN_LAST_NAME, customer.getLastName());
        values.put(COLUMN_TELEPHONE, customer.getTelephone());

        return  db.update(TABLE_CUSTOMER, values, _ID + "= ?",
                new String[] {String.valueOf(customer.getId()) });
    }

    //Delete Row
    //delete customer
    public  void deleteCustomer(long customerId){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, _ID + " = ?",
                new String[] {String.valueOf(customerId)});
    }

    //close DB
    public void closeDB(){
        SQLiteDatabase db= this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }
}
