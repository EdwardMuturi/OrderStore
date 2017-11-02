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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.sasalog.orderstore.myData.OrderProviderContract;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import static com.example.sasalog.orderstore.myData.OrderProviderContract.EDITOR_REQUEST_CODE;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter productCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setTitle("Products");

        FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.add_product);

        String[] from= {OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME};
        int [] to={android.R.id.text1};

        //returns error when product page runs
        productCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, from, to, 0);
        ListView productList= (ListView) findViewById(R.id.product_list);

        productList.setAdapter(productCursorAdapter);

        getLoaderManager().initLoader(0, null, this);

        restartLoader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id= item.getItemId();
//
//        switch (id){
////            case R.id.action_create_customer:
////                createCustomer();
////                break;
////            case R.id.action_delete_all:
////                deleteAllCustomers();
////                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void insertProduct(String productName) {
        ContentValues values= new ContentValues();
        values.put(OrderStoreContract.OrderStoreEntry.COLUMN_PRODUCT_NAME, productName);
        Uri productUri= getContentResolver().insert(OrderProviderContract.PRODUCT_CONTENT_URI, values);

        Log.d("ProductActivity", "Product Added" + productUri.getLastPathSegment());
    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, OrderProviderContract.PRODUCT_CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        productCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        productCursorAdapter.swapCursor(null);
    }

    public void editProduct(View view){
        Intent intent= new Intent(this, EditProductActivity.class);
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK){
            restartLoader();
        }
    }
}
