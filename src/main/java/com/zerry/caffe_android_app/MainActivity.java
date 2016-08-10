package com.zerry.caffe_android_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import mehdi.sakout.fancybuttons.FancyButton;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 20, 10, 20);
        LinearLayout mainui = (LinearLayout)findViewById(R.id.mainui);

        TaskSpec tasklist = new TaskSpec();
        for (final TaskSpec.SingleTask task : tasklist.tasks) {
            FancyButton btn = new FancyButton(this);
            btn.setText(task.taskName);
            btn.setBackgroundColor(Color.parseColor("#7ab800"));
            btn.setFocusBackgroundColor(Color.parseColor("#9bd823"));
            btn.setTextColor(Color.parseColor("#3b5998"));
            btn.setBorderColor(Color.parseColor("#FFFFFF"));
            btn.setBorderWidth(5);
            btn.setPadding(80, 10, 80, 20);
            btn.setTextSize(24);
            btn.setRadius(80);

            btn.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
                    i.putExtra("task", task);
                    startActivity(i);
                }
            });

            mainui.addView(btn, layoutParams);
        }


    }
}
