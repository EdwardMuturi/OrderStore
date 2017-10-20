package com.example.sasalog.orderstore;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import static com.example.sasalog.orderstore.myData.OrderProviderContract.AUTHORITY;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER_BASE_PATH;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER_ID;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.PRODUCT;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.PRODUCT_BASE_PATH;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.PRODUCT_ID;

/**
 * Created by sasalog on 9/18/17.
 */

public class OrderProvider extends ContentProvider {

    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, CUSTOMER_BASE_PATH, CUSTOMER);
        uriMatcher.addURI(AUTHORITY, CUSTOMER_BASE_PATH + "/#", CUSTOMER_ID);
        uriMatcher.addURI(AUTHORITY, PRODUCT_BASE_PATH, PRODUCT);
        uriMatcher.addURI(AUTHORITY, PRODUCT_BASE_PATH + "#", PRODUCT_ID);
    }

    private SQLiteDatabase database;
    @Override
    public boolean onCreate() {
        DatabaseHelper helper= new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[]
//            selectionArgs, @Nullable String sortOrder) {
//        if(uriMatcher.match(uri)== CUSTOMER_ID) {
//            selection = BaseColumns._ID + "=" + uri.getLastPathSegment();
//        }
//            return database.query(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS,
//                    selection, null, null, null,
//                    BaseColumns._ID + " DESC");
//
//    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[]
            selectionArgs, @Nullable String sortOrder) {
        String tableName= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
        String[] columnRange= OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS;
        Cursor myCursor = null;
        int match =uriMatcher.match(uri);

        switch (match){
            case CUSTOMER:
//                myCursor= database.query(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS,
//                        selection, null, null, null,
//                        BaseColumns._ID + " DESC");
                  tableName= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
                columnRange= OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS;
                break;
            case CUSTOMER_ID:
                selection = BaseColumns._ID + "=" + uri.getLastPathSegment();
                break;
            case PRODUCT:
//                myCursor= database.query(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT, OrderStoreContract.OrderStoreEntry.ALL_PRODUCTS, selection, null, null, null,
//                        BaseColumns._ID + " ASC");
                tableName= OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT;
                columnRange= OrderStoreContract.OrderStoreEntry.ALL_PRODUCTS;
                break;

            default:
                return myCursor;
        }
        return database.query(tableName, columnRange, selection, null, null, null,
                BaseColumns._ID + " ASC");
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri _uri= null;
        switch(uriMatcher.match(uri)){
            case CUSTOMER:
                long id =database.insert(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, null, contentValues );
               _uri= Uri.parse(CUSTOMER_BASE_PATH + "/" + id);
                break;
            case PRODUCT:
                long productId =database.insert(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT, null, contentValues );
                _uri= Uri.parse(PRODUCT_BASE_PATH + "/" + productId);
            default:
                Log.d("OrderProviderInsert", "Error inserting data");
                break;
        }
        return _uri;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
//        int deletedValue = 0;
//        switch (uriMatcher.match(uri)) {
//            case CUSTOMER:
                return database.delete(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, s, strings);
//                break;
////            case PRODUCT:
////                deletedValue = database.delete(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT, s, strings);
////                break;
//////            case CUSTOMER_ID:
//////                deletedValue = database.delete(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, s, strings);
//////                break;
//////            case PRODUCT_ID:
//////                deletedValue = database.delete(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT, s, strings);
//////                break;
////            default:
////                Log.d("OrderProviderDelete", "Error Deleting data");
////                break;
////        }
////        return deletedValue;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return database.update(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, contentValues, s, strings);
    }
}
