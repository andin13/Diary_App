package com.example.testapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TasksDbManager {
    private final TasksDbHelper tasksDbHelper;
    private SQLiteDatabase db;

    public TasksDbManager(Context context) {
        tasksDbHelper = new TasksDbHelper(context);
    }

    public void openDb() {
        db = tasksDbHelper.getWritableDatabase();
    }

    public void insertToDb(String json) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.JSON, json);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }

    public List<String> getFromDb() {
        List<String> jsonList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String json = cursor.getString(cursor.getColumnIndexOrThrow(MyConstants.JSON));
            jsonList.add(json);
        }
        cursor.close();
        return jsonList;
    }

    public void closeDb() {
        tasksDbHelper.close();
    }
}
