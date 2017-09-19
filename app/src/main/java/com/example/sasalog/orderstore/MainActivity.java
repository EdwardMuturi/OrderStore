package com.example.sasalog.orderstore;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS= 100;

    private CustomerAdapter mAdapter;
    private RecyclerView customerList;

   // DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerList= (RecyclerView) findViewById(R.id.customer_recycler);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        customerList.setLayoutManager(layoutManager);
        customerList.setHasFixedSize(true);

        mAdapter= new CustomerAdapter(NUM_LIST_ITEMS);

        customerList.setAdapter(mAdapter);

        insertCustomer("New Customer");


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
