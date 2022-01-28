package com.example.testapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TasksDbHelper extends SQLiteOpenHelper {

    public TasksDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyConstants.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(MyConstants.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
