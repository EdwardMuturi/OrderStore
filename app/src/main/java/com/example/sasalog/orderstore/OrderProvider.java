package com.example.sasalog.orderstore;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.sasalog.orderstore.myData.DatabaseHelper;
import com.example.sasalog.orderstore.myData.OrderStoreContract;

import static android.content.ContentValues.TAG;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.AUTHORITY;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER_BASE_PATH;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.CUSTOMER_ID;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER_BASE_PATH;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER_ID;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER_LIST;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER_LIST_BASE_PATH;
import static com.example.sasalog.orderstore.myData.OrderProviderContract.ORDER_LIST_ID;
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
        uriMatcher.addURI(AUTHORITY, PRODUCT_BASE_PATH + "/#", PRODUCT_ID);

        uriMatcher.addURI(AUTHORITY,ORDER_BASE_PATH, ORDER);
        uriMatcher.addURI(AUTHORITY,ORDER_BASE_PATH + "/#", ORDER_ID);

        uriMatcher.addURI(AUTHORITY,ORDER_LIST_BASE_PATH , ORDER_LIST);
        uriMatcher.addURI(AUTHORITY,ORDER_LIST_BASE_PATH + "/#", ORDER_LIST_ID);
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
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[]
            selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder builder= new SQLiteQueryBuilder();
        int match =uriMatcher.match(uri);

        switch (match){
            case CUSTOMER:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER);
                break;

            case CUSTOMER_ID:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER);
                builder.appendWhere(BaseColumns._ID + "= " + uri.getLastPathSegment());
                break;

            case PRODUCT:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT);
                break;
            case PRODUCT_ID:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT);
                builder.appendWhere(BaseColumns._ID + "= " + uri.getLastPathSegment());
                break;
            case ORDER:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_ORDERS);
                break;
            case ORDER_ID:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_ORDERS);
                builder.appendWhere(BaseColumns._ID + "= " +uri.getLastPathSegment());
                break;
            case ORDER_LIST:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_ORDERLIST);
                break;
            case ORDER_LIST_ID:
                builder.setTables(OrderStoreContract.OrderStoreEntry.TABLE_ORDERLIST);
                builder.appendWhere(BaseColumns._ID + "= " + uri.getLastPathSegment());
                break;

            default:
               throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

     Cursor cursor= builder.query(database, projection, selection, selectionArgs, null, null, null, sortOrder);
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if ( (uriMatcher.match(uri) != CUSTOMER) && (uriMatcher.match(uri) != PRODUCT) && (uriMatcher.match(uri) != ORDER) && (uriMatcher.match(uri) != ORDER_LIST)){
            throw new IllegalArgumentException("Unsopported URI for insertion: " + uri);
        }

        switch(uriMatcher.match(uri)){
            case CUSTOMER:
                long custId =database.insert(OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER, null, contentValues );
                getContext().getContentResolver().notifyChange(uri, null);
               return Uri.parse(CUSTOMER_BASE_PATH + "/" + custId);

            case PRODUCT:
                long productId =database.insert(OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT, null, contentValues );
                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(PRODUCT_BASE_PATH + "/" + productId);

            case ORDER:
                long orderId= database.insert(OrderStoreContract.OrderStoreEntry.TABLE_ORDERS, null, contentValues);
                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(ORDER_BASE_PATH +"/" + orderId);

            default:
                throw new SQLException("Failed to insert row into " + uri);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        DatabaseHelper  helper= new DatabaseHelper(getContext());
        database= helper.getWritableDatabase();
        int deletedRows= 0;
        String table= "";
        String whereClause= "";

        switch (uriMatcher.match(uri)) {
            case CUSTOMER:
                table= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
                whereClause= s;
                break;
            case ORDER:
                table= OrderStoreContract.OrderStoreEntry.TABLE_ORDERS;
                whereClause= s;
            case PRODUCT:
                table= OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT;
                whereClause= s;
                break;
            case CUSTOMER_ID:
                String customerId= uri.getLastPathSegment();
                table= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
                whereClause= BaseColumns._ID + "=" + customerId;
                if (TextUtils.isEmpty(s)){
                    s= null;
                } else {
                    whereClause += " and " + s;
                }
                break;
            case PRODUCT_ID:
                String productId= uri.getLastPathSegment();
                table= OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT;
                whereClause= BaseColumns._ID + "=" + productId;
                if (TextUtils.isEmpty(s)){
                    s= null;
                } else {
                    whereClause += " and " + s;
                }
                break;
            case ORDER_ID:
                String orderId= uri.getLastPathSegment();
                table= OrderStoreContract.OrderStoreEntry.TABLE_ORDERS;
                whereClause= BaseColumns._ID + "=" + orderId;
                if (TextUtils.isEmpty(s)){
                    s= null;
                } else {
                    whereClause += " and " + s;
                }
                break;
            default:
                Log.d("OrderProviderDelete", "Error Deleting data");
                break;
        }
        deletedRows= database.delete(table, whereClause, strings);
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
         DatabaseHelper helper= new DatabaseHelper(getContext());
         database= helper.getWritableDatabase();
        int rowsUpdated= 0;
        String foundTable= " ";
        String whereClause= " ";

        switch (uriMatcher.match(uri)){
            case CUSTOMER:
                foundTable= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
                whereClause= s;
                break;
            case CUSTOMER_ID:
                String id= uri.getLastPathSegment();
                foundTable= OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
                whereClause= BaseColumns._ID + "=" + id;
                if (TextUtils.isEmpty(s)){
                    s= null;
                } else {
                    whereClause += " and" +s;
                }
                break;
            case ORDER:
                foundTable= OrderStoreContract.OrderStoreEntry.TABLE_ORDERS;
                whereClause= s;
            case ORDER_ID:
                String orderId= uri.getLastPathSegment();
                foundTable= OrderStoreContract.OrderStoreEntry.TABLE_ORDERS;
                whereClause= BaseColumns._ID + "=" + orderId;
                if (TextUtils.isEmpty(s)){
                    s= null;
                } else {
//                    whereClause += " and " +s;
                    whereClause= s;
                }
                break;

            default:
                throw new SQLException("Failed to UPDATE row into " + uri);
        }
        rowsUpdated= database.update(foundTable, contentValues, whereClause, strings);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
