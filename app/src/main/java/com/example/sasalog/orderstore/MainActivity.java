package com.example.sasalog.orderstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS= 100;

    private CustomerAdapter mAdapter;
    private RecyclerView customerList;

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
    }
}
