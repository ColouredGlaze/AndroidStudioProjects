package com.example.lin.mt.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateDatabase;

    private Button btnAddBookData;

    private Button btnUpdateBookData;

    private Button btnDeleteBookData;

    private Button btnQueryBookData;

    private MyDatabaseHelper myDatabaseHelper;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        btnCreateDatabase = findViewById(R.id.create_database);
        btnAddBookData = findViewById(R.id.add_book_data);
        btnUpdateBookData = findViewById(R.id.update_book_data);
        btnDeleteBookData = findViewById(R.id.delete_book_data);
        btnQueryBookData = findViewById(R.id.query_book_data);
        btnCreateDatabase.setOnClickListener(this);
        btnAddBookData.setOnClickListener(this);
        btnUpdateBookData.setOnClickListener(this);
        btnDeleteBookData.setOnClickListener(this);
        btnQueryBookData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (v.getId()){
            case R.id.create_database:
                myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.add_book_data:
                contentValues.put("name", "The Da Z Code");
                contentValues.put("author", "DanBrow");
                contentValues.put("pages", 54);
                contentValues.put("price", 16.96);
                db.insert("BOOK", null, contentValues);
                contentValues.clear();
                contentValues.put("name", "The Last Simple");
                contentValues.put("author", "Tom");
                contentValues.put("pages", 160);
                contentValues.put("price", 63.99);
                db.insert("BOOK", null, contentValues);
                contentValues.clear();
                Toast.makeText(this, "Add finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_book_data:
                contentValues.put("price", 66.63);
                db.update("BOOK", contentValues, "name = ? ",new String[]{"The Da Z Code"});
                Toast.makeText(this, "Update finish", Toast.LENGTH_SHORT).show();
                contentValues.clear();
                break;
            case R.id.delete_book_data:
                db.delete("BOOK", "author = ? ", new String[]{"Tom"});
                Toast.makeText(this, "Delete finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_book_data:
                Cursor cursor = db.query("BOOK", null, null,
                        null, null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        Log.d(TAG, "name: " + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d(TAG, "author: " + cursor.getString(cursor.getColumnIndex("author")));
                        Log.d(TAG, "pages: " + cursor.getInt(cursor.getColumnIndex("pages")));
                        Log.d(TAG, "price: " + cursor.getDouble(cursor.getColumnIndex("price")));
                    } while (cursor.moveToNext());
                }
                Toast.makeText(this, "Query finish", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
