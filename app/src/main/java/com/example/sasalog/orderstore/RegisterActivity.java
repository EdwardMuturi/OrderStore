package com.example.sasalog.orderstore;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class RegisterActivity extends Activity  {

    public static String Extra_Customer_NAME = "com.sasalog.orderstore.MESSAGE";
    AutoCompleteTextView mCustomerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        Spinner spinner= (Spinner) findViewById(R.id.priviledge_spinner);
        //use access_levels_array  in string array and default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter= ArrayAdapter.createFromResource(this, R.array.access_levels_array,
                android.R.layout.simple_spinner_item);
        //set spinner layout in displayed list
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply spiner adapter
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentValues values= new ContentValues();
                values.put(OrderStoreContract.OrderStoreEntry.COLUMN_PRIVILEDGE, R.array.access_levels_array);
                Uri customerUri= getContentResolver().insert(OrderProviderContract.CUSTOMER_CONTENT_URI, values);

                Log.d("MainActivity", R.array.access_levels_array + customerUri.getLastPathSegment());
//                Toast.makeText(this, "Item selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void signIn(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        mCustomerName = (AutoCompleteTextView) findViewById(R.id.register_customer_name);
        String registeredUser= mCustomerName.getText().toString();
        intent.putExtra(Extra_Customer_NAME, registeredUser);
        startActivity(intent);
    }


}
