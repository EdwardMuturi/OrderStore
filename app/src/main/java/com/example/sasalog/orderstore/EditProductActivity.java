package com.example.sasalog.orderstore;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class EditProductActivity extends AppCompatActivity {

    private String action;
    private EditText productEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edit  Product");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productEditor= (EditText) findViewById(R.id.txt_edit_product);

        Intent intent= getIntent();

        Uri uri=intent.getParcelableExtra(OrderProviderContract.PRODUCT_CONTENT_ITEM_TYPE);

        if(uri == null){
            action= Intent.ACTION_INSERT;
            setTitle(getString(R.string.new_product));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        switch (id){
            case R.id.home:
                finishEditing();
                break;
        }

        return true;
    }

    private void finishEditing(){
    String newProduct= productEditor.getText().toString().trim();

    switch(action){
        case Intent.ACTION_INSERT:
            if(newProduct.length() == 0){
                setResult(RESULT_CANCELED);
            }else  {
                insertProduct(newProduct);
            }
    }
}

    private void insertProduct(String productName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME, productName);
        getContentResolver().insert(OrderProviderContract.PRODUCT_CONTENT_URI, values);
        setResult(RESULT_OK);
    }

    @Override
    public void onBackPressed() {
        finishEditing();
    }

}
