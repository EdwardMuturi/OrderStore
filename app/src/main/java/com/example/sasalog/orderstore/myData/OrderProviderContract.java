package com.example.sasalog.orderstore.myData;

import android.net.Uri;

/**
 * Created by sasalog on 9/30/17.
 */

public class OrderProviderContract {
    //Authority of content provider
    public static final String AUTHORITY = "com.example.orderstore.orderprovider";

    public static final String CUSTOMER_BASE_PATH = OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
    public static final Uri CUSTOMER_CONTENT_URI = Uri.parse("content://" + AUTHORITY+ "/" + CUSTOMER_BASE_PATH);

    public static  final String PRODUCT_BASE_PATH= OrderStoreContract.OrderStoreEntry.TABLE_PRODUCT;
    public static final Uri PRODUCT_CONTENT_URI= Uri.parse("content://" + AUTHORITY+ "/"+ PRODUCT_BASE_PATH);

    public  static final String ORDER_BASE_PATH= OrderStoreContract.OrderStoreEntry.TABLE_ORDERS;
    public static final Uri ORDER_CONTENT_URI= Uri.parse("content://" + AUTHORITY + "/" + ORDER_BASE_PATH);

    public  static final String ORDER_LIST_BASE_PATH= OrderStoreContract.OrderStoreEntry.TABLE_ORDERLIST;
    public static final Uri ORDER_LIST_CONTENT_URI= Uri.parse("content://" + AUTHORITY + "/" + ORDER_LIST_BASE_PATH);


    // Constant to identify the requested operation
    public static final int CUSTOMER = 1;
    public static final int CUSTOMER_ID = 2;

    public static final int PRODUCT= 3;
    public static final int PRODUCT_ID= 4;

    public static final int ORDER= 5;
    public static final int ORDER_ID= 6;

    public static final int ORDER_LIST= 7;
    public static final int ORDER_LIST_ID= 8;

// used indicate upate of an extsting note
    public static final String CONTENT_ITEM_TYPE="Customer";
    public static final String PRODUCT_CONTENT_ITEM_TYPE="Product";
    public static final String ORDER_ITEM_TYPE="Order";
    public static final String ORDER_LIST_TYPE="Order List";

    public static final int EDITOR_REQUEST_CODE = 1001; //use to identify the requesta after coming back from an activity
}
