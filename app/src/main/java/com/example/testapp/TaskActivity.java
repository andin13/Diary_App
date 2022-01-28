package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        TextView taskResult = findViewById(R.id.taskResult);
        Intent startIntent = getIntent();

        if (startIntent.hasExtra("TASK")) {
            String result = startIntent.getStringExtra("TASK");
            taskResult.setText(result);
        }

    }
}