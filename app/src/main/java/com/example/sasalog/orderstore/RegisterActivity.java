package com.example.sasalog.orderstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class RegisterActivity extends Activity {

    public static String Extra_User_NAME= "com.sasalog.orderstore.MESSAGE";
    AutoCompleteTextView mUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
    }

    public void signIn(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        mUserName= (AutoCompleteTextView) findViewById(R.id.register_user_name);
        String registeredUser= mUserName.getText().toString();
        intent.putExtra(Extra_User_NAME, registeredUser);
        startActivity(intent);
    }
}
