package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import com.example.testapp.utils.Task;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberList;
    private NumbersAdapter numbersAdapter;
    private TextView textView;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<String> dayTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int j = 1; j < 25; j++) {
            for (int k = 0; k < 3; k++) {
                tasks.add(new Task(j, j + "/1/2022", "task" + j, j + " января (" + j + "/1/2022) ", k));
            }
        }

        CalendarView mCalendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);
        numberList = findViewById(R.id.rv_numbers);
        numberList.setHasFixedSize(true);

        mCalendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            String name = i2 + "/" + (i1 + 1) + "/" + i;
            textView.setText("Заданий нет");
            dayTasks.clear();
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).name.equals(name)) {
                    dayTasks.add("Время: " + tasks.get(j).time);
                    textView.setText(tasks.get(j).description);
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            numberList.setLayoutManager(layoutManager);
            numbersAdapter = new NumbersAdapter(dayTasks.size(), MainActivity.this);
            numberList.setAdapter(numbersAdapter);

        });
    }
}