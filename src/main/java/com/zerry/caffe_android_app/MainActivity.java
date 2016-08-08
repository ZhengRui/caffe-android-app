package com.zerry.caffe_android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mehdi.sakout.fancybuttons.FancyButton;


public class MainActivity extends Activity {
    FancyButton obj_btn, face_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj_btn = (FancyButton) findViewById(R.id.btn_obj);
        face_btn = (FancyButton) findViewById(R.id.btn_face);

        obj_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
                i.putExtra("TASK", "objectRec");
                startActivity(i);
            }
        });

        face_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
                i.putExtra("TASK", "faceRec");
                startActivity(i);
            }
        });
    }
}
