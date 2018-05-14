package com.example.lin.mt.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by MT-Lin on 2017/12/14.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String CREATE_BOOK = "CREATE TABLE BOOK ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "CREATE TABLE CATEGORY ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
//        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists BOOK");
        db.execSQL("drop table if exists CATEGORY");
        onCreate(db);
    }
}
