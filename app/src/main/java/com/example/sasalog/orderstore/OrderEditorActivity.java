package com.example.sasalog.orderstore;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class OrderEditorActivity extends AppCompatActivity {
    private String action;
    private EditText editOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_editor);
        setTitle("Edit Order");

        editOrder= (EditText) findViewById(R.id.txt_edit_order);

        Intent intent= getIntent();
        Uri uri= intent.getParcelableExtra(OrderProviderContract.ORDER_ITEM_TYPE);

        if(uri == null){
            action= intent.ACTION_INSERT;
            setTitle(getString(R.string.add_new_order));
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
        }
        finish();
    }

    private void insertOrder(String orderQuantity) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_QUANTITY, orderQuantity);
        getContentResolver().insert(OrderProviderContract.ORDER_CONTENT_URI, values);
        setResult(RESULT_OK);
    }

    public void addOrder(View view) {
        finishEditing();
    }
}
