package com.example.blinddateapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SDBHelper extends SQLiteOpenHelper {

    public SDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists survey ("
                + "gender text,"
                + "number text,"
                + "name text,"
                + "password text,"
                + "q1 int,"
                + "q2 int,"
                + "q3 int,"
                + "q4 int,"
                + "q5 int,"
                + "q6 int,"
                + "q7 int,"
                + "q8 int,"
                + "q9 int,"
                + "q10 text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists survey";
        db.execSQL(sql);
        onCreate(db);
    }
}
