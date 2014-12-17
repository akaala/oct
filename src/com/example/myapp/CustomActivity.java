package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CustomActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        TextView label = (TextView) findViewById(R.id.show_data);

        String name = getIntent().getExtras().getString("name");
        String grade = getIntent().getExtras().getString("grade");
        label.setText(name + ":" + grade);
    }

    public void doBook(View view) {

    }
}
