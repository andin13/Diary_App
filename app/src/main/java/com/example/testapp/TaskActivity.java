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

        TextView taskNameF = findViewById(R.id.taskNameF);
        TextView taskDateF = findViewById(R.id.taskDateF);
        TextView taskTimeF = findViewById(R.id.taskTimeF);
        TextView taskDescriptionF = findViewById(R.id.taskDescriptionF);

        Intent startIntent = getIntent();

        taskNameF.setText(startIntent.getStringExtra("NAME"));
        taskDateF.setText(startIntent.getStringExtra("DATE"));
        taskTimeF.setText(startIntent.getStringExtra("TIME"));
        taskDescriptionF.setText(startIntent.getStringExtra("DESCRIPTION"));
    }
}