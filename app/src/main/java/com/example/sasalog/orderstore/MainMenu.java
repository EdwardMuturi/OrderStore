package com.example.sasalog.orderstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //extract email string from sign in activity
        Intent intent= getIntent();
        String message= intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        //show email of logged in user
        TextView loggedUser= (TextView) findViewById(R.id.txt_from_login);
        loggedUser.setText(message);
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
        Intent intent= new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    public void openOrderList(View view){
        Intent intent= new Intent(this, OrderLinesActivity.class);
        startActivity(intent);
    }

}
