package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.layout;
import com.example.app1.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app1, menu);
        return true;
    }
    
}
