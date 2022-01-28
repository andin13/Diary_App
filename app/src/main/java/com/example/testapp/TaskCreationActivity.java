package com.example.testapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.utils.Task;

import java.util.Calendar;

public class TaskCreationActivity extends AppCompatActivity {

    public static int day = 0;
    public static int month = 0;
    public static int year = 0;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        Button createTaskBtn = findViewById(R.id.createTaskBtn);
        EditText taskName = findViewById(R.id.taskName);
        EditText taskDescription = findViewById(R.id.taskDescription);
        NumberPicker numberPicker = findViewById(R.id.durationPicker);
        numberPicker.setMaxValue(60);
        numberPicker.setMinValue(0);

        Bundle extras = getIntent().getExtras();

        day = extras.getInt("day");
        month = extras.getInt("month");
        year = extras.getInt("year");
        dateAndTime.set(Calendar.DAY_OF_MONTH, day);
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, month);

        createTaskBtn.setOnClickListener(view -> {
            Task task = new Task(setInitialDateTime(),
                    (setInitialDateTime() + numberPicker.getValue() * 60000L),
                    taskName.getText().toString(),
                    taskDescription.getText().toString());
            sendJson(Task.createJson(task));
        });


    }

    private void sendJson(String json) {
        Intent data = new Intent();
        data.putExtra(MainActivity.GET_JSON, json);
        setResult(RESULT_OK, data);
        finish();
    }

    public void setTime(View v) {
        new TimePickerDialog(TaskCreationActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    public long setInitialDateTime() {
        return dateAndTime.getTimeInMillis();
    }

    TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        setInitialDateTime();
    };

}