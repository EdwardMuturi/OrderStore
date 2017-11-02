package com.example.sasalog.orderstore;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import java.lang.ref.ReferenceQueue;

import static com.example.sasalog.orderstore.myData.OrderProviderContract.EDITOR_REQUEST_CODE;

public class OrdersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        setTitle("Orders");
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.orders_fab);

        String[] from = {OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY};
        int[] to = {android.R.id.text1};
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, from, to, 0);

        ListView ordersList = (ListView) findViewById(R.id.orders_list);
        ordersList.setAdapter(cursorAdapter);

        getLoaderManager().initLoader(0, null,this);
    }
    //insert new order
    public void insertOrder(String orderQuantity){
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY, orderQuantity);
        Uri orderUri= getContentResolver().insert(OrderProviderContract.ORDER_CONTENT_URI, values);

        Log.d("OrdersActivity", "Inserted Order" + orderUri.getLastPathSegment());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, OrderProviderContract.ORDER_CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    public void openOrderEditor(View view) {
        Intent intent= new Intent(this, OrderEditorActivity.class);
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
    }
}
