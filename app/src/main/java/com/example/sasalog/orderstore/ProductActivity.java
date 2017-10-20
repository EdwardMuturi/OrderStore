package com.example.sasalog.orderstore;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class ProductActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setTitle("Products");

        Cursor cursor= getContentResolver().query(OrderProviderContract.PRODUCT_CONTENT_URI, OrderStoreContract.OrderStoreEntry.ALL_PRODUCTS,
                null, null ,null, null);
        String[] from= {OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME};
        int [] to={android.R.id.text1};

        CursorAdapter productCursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);//returns error when product page runs
        ListView productList= (ListView) findViewById(R.id.product_list);

        productList.setAdapter(productCursorAdapter);
        insertProduct("Ugali Beef");
    }

    private void insertProduct(String productName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME, productName);
        Uri productUri= getContentResolver().insert(OrderProviderContract.PRODUCT_CONTENT_URI, values);

        Log.d("ProductActivity", "Product Added" + productUri.getLastPathSegment());
    }
}
