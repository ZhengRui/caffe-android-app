package com.zerry.caffe_android_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zerry on 8/8/16.
 */
public class CategoriesActivity extends Activity {
    private static final String TAG = "CategoriesActivity";
    private String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        Bundle extras = getIntent().getExtras();
        task = extras.getString("TASK");
        Log.i(TAG, task);
    }
}
