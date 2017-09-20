package com.example.sasalog.orderstore;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import java.util.List;

public class MainActivity extends AppCompatActivity {


   // DatabaseHelper db;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertCustomer("New Customer");

        Cursor cursor= getContentResolver().query(OrderProvider.CONTENT_URI, OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS, null, null, null, null);
        String [] from= {OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME};
        int [] to= {android.R.id.text1};
        CursorAdapter cursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);

        ListView list= (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        /*db= new DatabaseHelper(getApplicationContext());
        Customers cust1= new Customers( );
        cust1.setFirstName("Edward");
        cust1.setLastName("Muturi");
        cust1.setTelephone("0716238698");

        Customers cust2= new Customers( );
        cust2.setFirstName("Edd");
        cust2.setLastName("Mutwiri");
        cust2.setTelephone("0717238698");

        db.createCustomer(cust1);
        db.createCustomer(cust2);

        Log.d("Get Customers", "Getting all Customers");
        List<Customers> allCustomers= db.getAllCustomers();
        for(Customers customer : allCustomers){
            Log.d("Customer", " " +customer.getId()+" "+customer.getFirstName()+" "+ customer.getLastName()+" " +customer.getTelephone());
        }

        db.closeDB();*/
    }

    private void insertCustomer(String customerFName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME, customerFName);
        Uri customerUri= getContentResolver().insert(OrderProvider.CONTENT_URI, values);

        Log.d("MainActivity", "Added Customer" + customerUri.getLastPathSegment());
    }
}
