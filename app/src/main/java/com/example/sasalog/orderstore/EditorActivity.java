package com.example.sasalog.orderstore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

public class EditorActivity extends AppCompatActivity {
    private  String Action; //reminds what's being done e.g adding or delete
    private EditText editor;
    private String customerFilter;
    private String oldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editor= (EditText) findViewById(R.id.edit_text);

        Intent intent= getIntent();
        Uri uri= intent.getParcelableExtra(OrderProviderContract.CONTENT_ITEM_TYPE);

        if(uri == null){
            Action= intent.ACTION_INSERT;
            setTitle(getString(R.string.new_customer));
        } else {
            Action= Intent.ACTION_EDIT;
            customerFilter= BaseColumns._ID + "=" + uri.getLastPathSegment();

            Cursor cursor= getContentResolver().query(uri, OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS, customerFilter, null, null);
            cursor.moveToFirst();
            oldText= cursor.getString(cursor.getColumnIndex(OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME));
            editor.setText(oldText);
            editor.requestFocus();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (Action.equals(Intent.ACTION_EDIT)){
            getMenuInflater().inflate(R.menu.menu_editor, menu);
    }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        switch (item.getItemId()){
            case android.R.id.home:
                finishEditing();
                break;
            case R.id.action_delete:
                deleteCustomer();
        }

        return true;
    }

    private void deleteCustomer() {
        getContentResolver().delete(OrderProviderContract.CONTENT_URI, customerFilter, null);
        Toast.makeText(this, R.string.customer_deleted, Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();// return to main activity
    }

    private void finishEditing() {
        String newText= editor.getText().toString().trim();

        switch (Action){
            case Intent.ACTION_INSERT:
                if (newText.length() == 0){
                    setResult(RESULT_CANCELED);
                } else{
                    insertCustomer(newText);
                }
                break;
            case Intent.ACTION_EDIT:
                if(newText.length()== 0){
                    deleteCustomer();
                } else if(oldText.equals(newText)){
                    setResult(RESULT_CANCELED);
                } else {
                    updateCustomer(newText);
                }
        }
        finish();
    }

    private void updateCustomer(String customerFName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME, customerFName);
        getContentResolver().update(OrderProviderContract.CONTENT_URI, values, customerFilter, null);

        Toast.makeText(this, R.string.customer_updated_Toast, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }

    private void insertCustomer(String customerFName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_FIRST_NAME, customerFName);
        getContentResolver().insert(OrderProviderContract.CONTENT_URI, values);
        setResult(RESULT_OK);
    }

    @Override
    public void onBackPressed(){
        finishEditing();
    }

}
