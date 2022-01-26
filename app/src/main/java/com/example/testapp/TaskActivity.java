package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        TextView taskResult = findViewById(R.id.taskResult);

        Intent startIntent = getIntent();

        if (startIntent.hasExtra("NAME")) {
            String result = startIntent.getStringExtra("NAME");
            taskResult.setText(result);
        }


    }
}