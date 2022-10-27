package com.example.myapplication.DataBase;

public class MyConstants {
    /*
    Створили константи для SQLite
    https://developer.android.com/training/data-storage/sqlite - змінні брались тут
    https://youtu.be/wwq4I8sLfzE?list=PLmjT2NFTgg1fPdNnJ3Rf2gNSbWZuMkkTi - з цим чуваком
     */
    public static final String LIST_ITEM_INTENT = "list_item_intent";
    public static final String EDIT_STATE = "edit_state";
    public static final String TABLE_NAME = "My_table"; //назва таблиці
    public static final String _ID = "_id"; //назва першої колони, де буде зберігатись ідентифікатор кожного елементу бази даних
    public static final String TITLE = "title"; //назва другої колони
    public static final String DISC = "disc"; // // description з англ. "Опис"
    public static final String DB_NAME = "my_db.db"; //название самой бази даних
    public static final int DB_VERSION = 2;// версія бази даних
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + //структура таблиці
            " (" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + DISC + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME; //щоб видаляти таблицю

}
