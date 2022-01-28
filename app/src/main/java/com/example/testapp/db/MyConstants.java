package com.example.testapp.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String JSON = "json";
    public static final String DB_NAME = "tasks_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + JSON + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
