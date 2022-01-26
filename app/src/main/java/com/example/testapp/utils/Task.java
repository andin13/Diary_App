package com.example.testapp.utils;

public class Task {
    public int id, time;
    public String name, description, date;

    public Task(int id, String name, String date, String description, int time) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.time = time;
    }

}
