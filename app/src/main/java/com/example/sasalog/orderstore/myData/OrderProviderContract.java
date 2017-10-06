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


    // Constant to identify the requested operation
    public static final int CUSTOMER = 1;
    public static final int CUSTOMER_ID = 2;

    public static final int PRODUCT= 3;
    public static final int PRODUCT_ID= 4;

    public static final String CONTENT_ITEM_TYPE="Customer";
    public static final String PRODUCT_CONTENT_ITEM_TYPE="Product";

    public static final int EDITOR_REQUEST_CODE = 1001;
}
