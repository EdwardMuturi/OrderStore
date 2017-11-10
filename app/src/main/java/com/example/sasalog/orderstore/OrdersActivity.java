package com.example.sasalog.orderstore;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //parent, view, position, id -> easy order names of below parameters
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(OrdersActivity.this, OrderEditorActivity.class);
                Uri uri= Uri.parse(OrderProviderContract.ORDER_CONTENT_URI + "/" + l);
                intent.putExtra(OrderProviderContract.ORDER_ITEM_TYPE, uri);
                startActivityForResult(intent, EDITOR_REQUEST_CODE);

            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        
        switch (id){
            case R.id.action_delete_order:
                deleteAllOrders();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllOrders() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {
                            //pass null value to where clause to delete all records
                            getContentResolver().delete(OrderProviderContract.ORDER_CONTENT_URI, null, null);
                            restartLoader();
                            Toast.makeText(OrdersActivity.this, R.string.toast_all_orders_deleted, Toast.LENGTH_SHORT).show();
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msg_delete_all_orders)
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }

    private void restartLoader() {
//        getSupportLoaderManager().restartLoader(0, null,  this);
        getLoaderManager().initLoader(1,null, this);
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
        restartLoader();
    }
}
