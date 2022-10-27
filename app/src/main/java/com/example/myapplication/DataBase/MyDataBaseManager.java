package com.example.myapplication.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseManager {
    private Context contex;
    private MyDataBaseHelper myDataBaseHelper;
    private SQLiteDatabase db;

    public MyDataBaseManager(Context contex) {
        this.contex = contex;
        myDataBaseHelper = new MyDataBaseHelper(contex);
    }
    public void openDataBase() {
        db = myDataBaseHelper.getWritableDatabase();
    }
    public void insertToDb(String title, String disc) {

        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DISC, disc);

        db.insert(MyConstants.TABLE_NAME, null, cv);

    }

    public void updateItem(String title, String disc, int id) {
        String selection = MyConstants._ID + "=" + id;
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DISC, disc);
        db.update(MyConstants.TABLE_NAME, cv, selection, null);
    }

    public void delete(int id) {
        String selection = MyConstants._ID + "=" + id;
        db.delete(MyConstants.TABLE_NAME, selection, null);
    }

    public List<ListItem> getFromDb() {
        List<ListItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,null,null,
                null,null,null, null);

        while(cursor.moveToNext()) {
            ListItem item = new ListItem();

            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex(MyConstants.DISC));
            @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex(MyConstants._ID));

            item.setTitle(title);
            item.setDesc(desc);
            item.setId(_id);
            tempList.add(item);
        }
        cursor.close();
        return tempList;
    }

    public ListItem getFromDbWithQuery(String query) {
        ListItem item = null;
        String selection = MyConstants.TITLE + " like ?";
        Cursor cursor = db.query(MyConstants.TABLE_NAME,null,selection,
                new String[]{"%" + query + "%"},null,null, null);
        while(cursor.moveToNext()) {
            item = new ListItem();

            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex(MyConstants.DISC));
            @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex(MyConstants._ID));
            item.setTitle(title);
            item.setDesc(desc);
            item.setId(_id);
        }
        cursor.close();
        return item;
    }

    public  void closeDb() {
        myDataBaseHelper.close();
    }
}
