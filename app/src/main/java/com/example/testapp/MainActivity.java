package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.db.TasksDbManager;
import com.example.testapp.utils.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String GET_JSON = "GET_JSON";

    private RecyclerView numberList;
    private TasksDbManager tasksDbManager;
    public static ArrayList<String> tasks = new ArrayList<>();
    public static ArrayList<String> dayTasks = new ArrayList<>();
    public static int chosenDay = 0;
    public static int chosenMonth = 0;
    public static int chosenYear = 0;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        assert intent != null;
                        String json = intent.getStringExtra(GET_JSON);
                        tasksDbManager.insertToDb(json);
                        tasks.add(json);

                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksDbManager = new TasksDbManager(this);
        CalendarView mCalendarView = findViewById(R.id.calendarView);
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        numberList = findViewById(R.id.rv_numbers);
        numberList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        numberList.setLayoutManager(layoutManager);

        addTaskBtn.setOnClickListener(view -> {

            if (chosenDay == 0 && chosenMonth == 0 && chosenYear == 0) {
                Toast toast = Toast.makeText(this,
                        "Выберите дату",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent taskCreationActivityIntent = new Intent(this, TaskCreationActivity.class);
                taskCreationActivityIntent.putExtra("day", (chosenDay));
                taskCreationActivityIntent.putExtra("month", (chosenMonth));
                taskCreationActivityIntent.putExtra("year", (chosenYear));
                mStartForResult.launch(taskCreationActivityIntent);
            }
        });

        mCalendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            chosenDay = i2;
            chosenMonth = i1;
            chosenYear = i;
            refreshRV(i, i1, i2);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasksDbManager.openDb();
        tasks.clear();
        tasks.addAll(tasksDbManager.getFromDb());
        refreshRV(chosenYear, chosenMonth, chosenDay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tasksDbManager.closeDb();
    }

    public void refreshRV(int i, int i1, int i2) {
        dayTasks.clear();
        i1++;
        String date;
        if (String.valueOf(i2).length() == 1 || String.valueOf(i1).length() == 1) {
            if (String.valueOf(i2).length() == 1 && String.valueOf(i1).length() == 1) {
                date = (i + "-" + "0" + i1 + "-" + "0" + i2);
            } else if (String.valueOf(i2).length() == 1) {
                date = (i + "-" + i1 + "-" + "0" + i2);
            } else {
                date = (i + "-" + "0" + i1 + "-" + i2);
            }
        } else {
            date = (i + "-" + i1 + "-" + i2);
        }
        for (int j = 0; j < tasks.size(); j++) {
            if (Task.getDate(tasks.get(j)).equals(date)) {
                dayTasks.add(tasks.get(j));
            }
        }
        NumbersAdapter numbersAdapter = new NumbersAdapter(dayTasks.size(), MainActivity.this, dayTasks);
        numberList.setAdapter(numbersAdapter);
    }
}