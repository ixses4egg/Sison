package com.example.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstants.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(MyConstants.DROP_TABLE);
        onCreate(db);
    }
}

