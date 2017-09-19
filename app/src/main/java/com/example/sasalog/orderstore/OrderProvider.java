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

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

/**
 * Created by sasalog on 9/18/17.
 */

public class OrderProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.orderstore.orderprovider";
    private static final String BASE_PATH = OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;

    private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, NOTES);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#", NOTES_ID);
    }

    private SQLiteDatabase database;
    @Override
    public boolean onCreate() {
        DatabaseHelper helper= new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return database.query(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, OrderStoreContract.OrderStoreEntry.ALL_CUSTOMERS,
                s, null, null, null,
                BaseColumns._ID + "DESC");
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id =database.insert(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, null, contentValues );
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return database.delete(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, s, strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return database.update(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, contentValues, s, strings);
    }
}
