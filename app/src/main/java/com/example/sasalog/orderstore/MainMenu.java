package com.example.sasalog.orderstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void openCustomers(View view){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openProducts(View view){
        Intent intent= new Intent(this, ProductActivity.class);
        startActivity(intent);

    }

    public void openOrders(View view){
        Intent intent= new Intent(this, Orders.class);
        startActivity(intent);
    }

    public void openOrderList(View view){
        Intent intent= new Intent(this, OrderLines.class);
        startActivity(intent);
    }

}
