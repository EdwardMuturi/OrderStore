package com.example.sasalog.orderstore.myData;

/**
 * Created by sasalog on 9/13/17.
 */
import android.provider.BaseColumns;
public class OrderStoreContract {
    public static final class OrderStoreEntry implements BaseColumns{
        //table names
        public  static final String TABLE_CUSTOMER = "Customers";
        public  static final String TABLE_PRODUCT = "product";
        public  static final String TABLE_ORDERS = "orders";
        public  static final String TABLE_ORDERLIST = "orderList";

        //Customer Table - column names
        public static final String COLUMN_FIRST_NAME="firstName";
        public static final String COLUMN_LAST_NAME= "lastName";
        public static final String COLUMN_TELEPHONE= "telephone";
        public  static final String[] ALL_CUSTOMERS= {BaseColumns._ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_TELEPHONE};

        //product Table -column names
        public static final String COLUMN_PRODUCT_NAME="productName";
        public static final String COLUMN_PRICE= "price";

        //Orders Table - column names
        public static final String COLUMN_QUANTITY= "quantity";
        public static final String COLUMN_PRODUCT_PRICE= "price";
        public static final String COLUMN_TOTAL_AMOUNT= "totalAmount";

        //Order Lines Table - column names
        public static final String COLUMN_CUSTOMER_ID="customerId";
        public static final String COLUMN_ORDER_ID= "orderId";
        public static final String COLUMN_PRODUCT_ID= "productId";

    }
}
