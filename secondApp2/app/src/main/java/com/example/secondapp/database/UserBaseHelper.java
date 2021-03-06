package com.example.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static  com.example.secondapp.database.UserDbSchema.*;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASENAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.NAME + " (" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID + "," +
                UserTable.Cols.FIRSTNAME + "," +
                UserTable.Cols.LASTNAME + "," +
                UserTable.Cols.PHONE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
