package com.example.sasalog.orderstore.myData;

import android.net.Uri;

/**
 * Created by sasalog on 9/30/17.
 */

public class OrderProviderContract {
    //Authority of content provider
    public static final String AUTHORITY = "com.example.orderstore.orderprovider";

    public static final String BASE_PATH = OrderStoreContract.OrderStoreEntry.TABLE_CUSTOMER;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY+ "/" + BASE_PATH );

    // Constant to identify the requested operation
    public static final int CUSTOMER = 1;
    public static final int CUSTOMER_ID = 2;
}
