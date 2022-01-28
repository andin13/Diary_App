package com.example.testapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.time.ZoneId;

public class Task {
    public long date_start, date_finish;
    public String name, description;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Task(long date_start, long date_finish, String name, String description) {
        this.date_start = date_start;
        this.date_finish = date_finish;
        this.name = name;
        this.description = description;
    }

    public static String createJson(Task task) {
        return GSON.toJson(task);
    }

    public static String getDate(String json) {
        return Instant.ofEpochMilli(GSON.fromJson(json, Task.class).date_start).atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    public static long getTimeStart(String json) {
        return GSON.fromJson(json, Task.class).date_start;
    }

    public static long getTimeFinish(String json) {
        return GSON.fromJson(json, Task.class).date_finish;
    }

    public static String getName(String json) {
        return GSON.fromJson(json, Task.class).name;
    }

    public static String getDescription(String json) {
        return GSON.fromJson(json, Task.class).description;
    }
}
