package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/13/17.
 */
import android.provider.BaseColumns;
public class CustomerContract {
    public static final class CustomerEntry implements BaseColumns{
        public  static final String TABLE_NAME= "Customers";
        public static final String COLUMN_FIRST_NAME="firstName";
        public static final String COLUMN_LAST_NAME= "lastName";
        public static final String COLUMN_TELEPHONE= "telephone";


    }
}
