package com.example.sasalog.orderstore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class OrderEditorActivity extends AppCompatActivity {
    private String action;
    private EditText editOrder;
    private String orderFilter;
    private String oldOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_editor);
        setTitle("Edit Order");

        editOrder= (EditText) findViewById(R.id.txt_edit_order);

        Intent intent= getIntent();
        Uri uri= intent.getParcelableExtra(OrderProviderContract.ORDER_ITEM_TYPE);

        if(uri == null){
            action= Intent.ACTION_INSERT;
            setTitle(getString(R.string.add_new_order));
        } else {
            action= Intent.ACTION_EDIT;
            orderFilter= BaseColumns._ID + "=" + uri.getLastPathSegment();

            Cursor cursor= getContentResolver().query(uri, OrderStoreContract.OrderStoreEntry.ALL_ORDERS, orderFilter, null, null);
            cursor.moveToFirst();
            oldOrder= cursor.getString(cursor.getColumnIndex(OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY));
            editOrder.setText(oldOrder);
            editOrder.requestFocus();
        }
    }

    private void finishEditing(){
        String newOrder= editOrder.getText().toString().trim();

        switch (action){
            case Intent.ACTION_INSERT:
                if(newOrder.length()== 0){
                    setResult(RESULT_CANCELED);
                } else {
                    insertOrder(newOrder);
                }
                break;
            case Intent.ACTION_EDIT:
                if(newOrder.length() == 0 ){
//                    deleteOrder()
                } else if(oldOrder.equals(newOrder)) {
                    setResult(RESULT_CANCELED);
                } else {
                    updateOrder(newOrder);
                }
        }
        finish();
    }

    private void updateOrder(String orderQuantity) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY, orderQuantity);
        getContentResolver().update(OrderProviderContract.ORDER_CONTENT_URI, values, orderFilter, null);
        Toast.makeText(this, R.string.order_updated_text, Toast.LENGTH_LONG).show();
        setResult(RESULT_OK);
    }

    private void insertOrder(String orderQuantity) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY, orderQuantity);
        getContentResolver().insert(OrderProviderContract.ORDER_CONTENT_URI, values);
        setResult(RESULT_OK);
    }

    public void addOrder(View view) {
        finishEditing();
        Toast.makeText(this, R.string.order_added_text, Toast.LENGTH_LONG).show();
    }

    public void orderUpdate(View view) {
        finishEditing();
        Toast.makeText(this, R.string.order_updated_text, Toast.LENGTH_LONG ).show();
    }
}
